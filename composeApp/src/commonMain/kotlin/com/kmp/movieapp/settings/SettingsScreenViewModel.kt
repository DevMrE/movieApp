package com.kmp.movieapp.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.open_settings.SettingsNavigator
import com.kmp.movieapp.core.permission.domain.Permission
import com.kmp.movieapp.core.permission.domain.PermissionsController
import com.kmp.movieapp.settings.model.PermissionDemoResult
import com.kmp.movieapp.settings.model.UiSettingsData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SettingsScreenViewModel(
    private val permissionsController: PermissionsController,
    private val settingsNavigator: SettingsNavigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiSettingsData())

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<UiSettingsData> = _uiState


    @OptIn(ExperimentalCoroutinesApi::class)
    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnPermissionClicked -> checkPermission(action.permission)
        }
    }

    private fun checkPermission(permission: Permission) {
        viewModelScope.launch {
            when (permission) {
                Permission.LOCATION -> handleLocationPermission()
                Permission.CAMERA -> handleCameraPermission()
                else -> Unit
            }
        }
    }

    private fun handleLocationPermission() {
        viewModelScope.launch {
            permissionsController.location().collectLatest { result ->
                result.onGranted { data ->
                    Logger.i(tag = "Permission", messageString = "viewModel onGranted")

                    _uiState.update {
                        it.copy(
                            permissionDemoResult = PermissionDemoResult.LocationReady(
                                data?.latitude,
                                data?.longitude
                            )
                        )
                    }
                }.onDenied {
                    Logger.i(tag = "Permission", messageString = "viewModel onDenied")

                    _uiState.update {
                        it.copy(
                            permissionDemoResult = PermissionDemoResult.PermissionDenied(
                                Permission.LOCATION
                            )
                        )
                    }
                }
            }
        }
    }

    private fun handleCameraPermission() {
        viewModelScope.launch {
            permissionsController.camera().collectLatest { result ->
                result.onGranted {
                    _uiState.update {
                        it.copy(
                            permissionDemoResult = PermissionDemoResult.CameraReady
                        )
                    }
                }.onDenied {

                }
            }
        }
    }
}