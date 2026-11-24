package com.kmp.movieapp.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.open_settings.SettingsNavigator
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.device_operations.domain.controller.DeviceOperationsController
import com.kmp.movieapp.device_operations.domain.result.OperationResult
import com.kmp.movieapp.device_operations.domain.result.onCancelled
import com.kmp.movieapp.device_operations.domain.result.onDenied
import com.kmp.movieapp.device_operations.domain.result.onGranted
import com.kmp.movieapp.more.model.Permission
import com.kmp.movieapp.more.model.PermissionDemoResult
import com.kmp.movieapp.more.model.UiSettingsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MoreScreenViewModel(
    private val deviceOperationsController: DeviceOperationsController,
    private val settingsNavigator: SettingsNavigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiSettingsData())

    val uiState: StateFlow<UiSettingsData> = _uiState


    fun onAction(action: MoreAction) {
        when (action) {
            is MoreAction.OnPermissionClicked -> checkPermission(action.permission)
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
                    logI<MoreScreenViewModel>(message = "viewModel getCurrentLocation granted")

                    _uiState.update {
                        it.copy(
                            permissionDemoResult = PermissionDemoResult.LocationReady(
                                data.latitude,
                                data.longitude
                            )
                        )
                    }
                }.onDenied {
                    _uiState.update {
                        it.copy(
                            permissionDemoResult = PermissionDemoResult.PermissionDenied(
                                Permission.LOCATION
                            )
                        )
                    }

                    settingsNavigator.openAppSettings()
                }.onCancelled {
                    // used to track example how many times the user doesn't interact
                    // with the permission dialog. In previous projects, it helps to
                    // debug on unexcepted bugs.
                    logI<MoreScreenViewModel>(message = "user canceled the location permission dialog")
                }
            }
        }
    }

    private fun handleCameraFeature() {
        viewModelScope.launch {
            deviceOperationsController.capturePhoto().collectLatest { result ->
                result.onGranted { data ->
                    logI<MoreScreenViewModel>(message = "capture photo granted")
                    _uiState.update {
                        it.copy(
                            permissionDemoResult = PermissionDemoResult.GalleryReady(
                                mediaList = listOf(
                                    data
                                )
                            )
                        )
                    }
                }.onDenied {
                    // After a request was denied, we could show some dialog with an explanation
                    // why we need this permission and redirect to the settings for granting it.
                    _uiState.update {
                        it.copy(
                            permissionDemoResult = PermissionDemoResult.PermissionDenied(
                                Permission.CAMERA
                            )
                        )
                    }
                }.onCancelled {
                    // used to track example how many times the user doesn't interact
                    // with the permission dialog. In previous projects, it helps to
                    // debug on unexcepted bugs.
                    logI<MoreScreenViewModel>(message = "user canceled the camera permission dialog")
                }
            }
        }
    }

    private fun handleGalleryFeature() {
        viewModelScope.launch {
            deviceOperationsController.pickImages().collectLatest { result ->
                when (result) {
                    is OperationResult.Success -> {
                        _uiState.update {
                            it.copy(
                                permissionDemoResult = PermissionDemoResult.GalleryReady(mediaList = result.data)
                            )
                        }
                    }

                    is OperationResult.Denied -> {
                        _uiState.update {
                            it.copy(
                                permissionDemoResult = PermissionDemoResult.PermissionDenied(
                                    Permission.GALLERY
                                )
                            )
                        }
                    }

                    is OperationResult.Cancelled -> {
                        logI<MoreScreenViewModel>(message = "user canceled the gallery permission dialog")
                    }
                }
            }
        }
    }
}