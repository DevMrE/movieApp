package com.kmp.movieapp.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.open_settings.SettingsNavigator
import com.kmp.movieapp.device_operations.domain.controller.DeviceOperationsController
import com.kmp.movieapp.device_operations.domain.result.OperationResult
import com.kmp.movieapp.device_operations.domain.result.onCancelled
import com.kmp.movieapp.device_operations.domain.result.onDenied
import com.kmp.movieapp.device_operations.domain.result.onGranted
import com.kmp.movieapp.settings.model.Permission
import com.kmp.movieapp.settings.model.PermissionDemoResult
import com.kmp.movieapp.settings.model.UiSettingsData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SettingsScreenViewModel(
    private val deviceOperationsController: DeviceOperationsController,
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
                Permission.LOCATION -> handleLocationFeature()
                Permission.CAMERA -> handleCameraFeature()
                Permission.GALLERY -> handleGalleryFeature()
                else -> Unit
            }
        }
    }

    private fun handleLocationFeature() {
        viewModelScope.launch {
            deviceOperationsController.getCurrentLocation().collectLatest { result ->
                result.onGranted { data ->
                    Logger.i(
                        tag = "Permission",
                        messageString = "viewModel getCurrentLocation granted"
                    )

                    _uiState.update {
                        it.copy(
                            permissionDemoResult = PermissionDemoResult.LocationReady(
                                data.latitude,
                                data.longitude
                            )
                        )
                    }
                }.onDenied {
                    Logger.i(tag = "Permission", messageString = "viewModel onDenied")
                }.onCancelled {
                    Logger.i(tag = "Permission", messageString = "viewModel onCancelled")

                }
            }
        }
    }

    private fun handleCameraFeature() {
        viewModelScope.launch {
            deviceOperationsController.capturePhoto().collectLatest { result ->
                when (result) {
                    is OperationResult.Success -> {
                        Logger.i(tag = "Permission", messageString = "capture photo granted")

                    }

                    is OperationResult.Denied -> {
                        Logger.i(tag = "Permission", messageString = "viewModel onDenied")
                    }

                    is OperationResult.Cancelled -> {

                    }
                }
            }
        }
    }

    private fun handleGalleryFeature() {
        viewModelScope.launch {
            deviceOperationsController.pickImages().collectLatest { result ->
                when (result) {
                    is OperationResult.Success -> {
                        Logger.i(tag = "Permission", messageString = "capture photo granted")
                        _uiState.update {
                            it.copy(
                                permissionDemoResult = PermissionDemoResult.GalleryReady(mediaList = result.data)
                            )
                        }
                    }

                    is OperationResult.Denied -> {
                        Logger.i(tag = "Permission", messageString = "viewModel onDenied")
                    }

                    is OperationResult.Cancelled -> {

                    }
                }
            }
        }
    }
}