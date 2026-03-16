package com.kmp.movieapp.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.util.permission.Permission
import com.kmp.movieapp.core.util.permission.PermissionRequester
import com.kmp.movieapp.core.util.permission.PermissionState
import com.kmp.movieapp.core.util.permission.location.LocationProvider
import com.kmp.movieapp.core.util.permission.require
import com.kmp.movieapp.core.util.permission.speech.SpeechRecognizer
import com.kmp.movieapp.core.util.permission.speech.SpeechResult
import com.kmp.movieapp.settings.model.PermissionDemoResult
import com.kmp.movieapp.settings.model.UiSettingsData
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SettingsScreenViewModel(
    private val permissionRequester: PermissionRequester,
    private val locationProvider: LocationProvider,
    private val speechRecognizer: SpeechRecognizer
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiSettingsData())
    val uiState = _uiState.asStateFlow()

    private var listeningJob: Job? = null

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnPermissionClicked -> checkPermission(action.permission)
        }
    }

    private fun checkPermission(permission: Permission) {
        viewModelScope.launch {
            when (permission) {
                Permission.LOCATION -> handleLocationPermission()
                else -> {}
            }
        }
    }

    private fun handleLocationPermission() {
        viewModelScope.launch {
            permissionRequester.require(
                permission = Permission.LOCATION,
                onGranted = {
                    locationProvider.getCurrentLocation()
                        .onSuccess { location ->
                            Logger.i("Permission", message = { "Location?: $location" })
                            handlePermissionOnResult(
                                PermissionDemoResult.LocationReady(
                                    lat = location.lat,
                                    lng = location.lng
                                )
                            )
                        }
                        .onFailure {
                            Logger.i(
                                "Permission",
                                message = { "Permission granted, but no data received" })
                            handlePermissionOnResult(
                                PermissionDemoResult.PermissionDenied(
                                    Permission.LOCATION
                                )
                            )
                        }
                },
                onDenied = { state ->
                    Logger.i("Permission", message = { "Permission denied" })
                    val result = when (state) {
                        PermissionState.PERMANENTLY_DENIED ->
                            PermissionDemoResult.PermissionPermanentlyDenied(Permission.LOCATION)

                        else -> PermissionDemoResult.PermissionDenied(Permission.LOCATION)
                    }

                    handlePermissionOnResult(result)
                }
            )
        }
    }

    private fun handlePermissionOnResult(result: PermissionDemoResult) {
        _uiState.update {
            it.copy(
                permissionDemoResult = result
            )
        }
    }

    private fun startListening() {
        viewModelScope.launch {
            permissionRequester.require(
                permission = Permission.MICROPHONE,
                onGranted = { beginListening() },
                onDenied = { state ->
                    _uiState.update {
                        it.copy(
                            voiceInputState = it.voiceInputState?.copy(
                                error = when (state) {
                                    PermissionState.PERMANENTLY_DENIED ->
                                        "Mikrofon dauerhaft verweigert – bitte in Einstellungen erlauben"

                                    else ->
                                        "Mikrofon Permission benötigt"
                                }
                            )
                        )
                    }
                }
            )
        }
    }


    private fun beginListening() {
        if (!speechRecognizer.isAvailable()) {
            _uiState.update { state ->
                state.copy(
                    voiceInputState = state.voiceInputState?.copy(error = "Spracherkennung nicht verfügbar")
                )
            }
            return
        }

        listeningJob = viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    voiceInputState = state.voiceInputState?.copy(
                        isListening = true,
                        error = null,
                        partialText = ""
                    )
                )
            }

            speechRecognizer.startListening().collect { result ->
                when (result) {
                    is SpeechResult.Partial ->
                        _uiState.update {
                            it.copy(
                                voiceInputState = it.voiceInputState?.copy(
                                    partialText = result.text
                                )
                            )
                        }

                    is SpeechResult.Final ->
                        _uiState.update {
                            it.copy(
                                voiceInputState = it.voiceInputState?.copy(
                                    text = buildString {
                                        if (this.isNotEmpty()) {
                                            append(it.voiceInputState.text)
                                            append(" ")
                                        }
                                        append(result.text)
                                    },
                                    partialText = "",
                                    isListening = false
                                )
                            )
                        }

                    is SpeechResult.NoMatch ->
                        _uiState.update {
                            it.copy(
                                voiceInputState = it.voiceInputState?.copy(
                                    partialText = "",
                                    isListening = false,
                                    error = "Nichts erkannt – bitte nochmal versuchen"
                                )
                            )
                        }

                    is SpeechResult.Stopped ->
                        _uiState.update {
                            it.copy(
                                voiceInputState = it.voiceInputState?.copy(
                                    isListening = false,
                                    partialText = ""
                                )
                            )
                        }
                }
            }
        }
    }

    private fun stopListening() {
        speechRecognizer.stopListening()
        listeningJob?.cancel()
        listeningJob = null
        _uiState.update {
            it.copy(
                voiceInputState = it.voiceInputState?.copy(
                    isListening = false,
                    partialText = ""
                )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        speechRecognizer.stopListening()
    }
}