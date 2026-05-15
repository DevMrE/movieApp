package com.kmp.movieapp.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.permission_camera
import com.kmp.movieapp.composeApp.permission_image_gallery
import com.kmp.movieapp.composeApp.permission_location
import com.kmp.movieapp.composeApp.permission_microphone
import com.kmp.movieapp.core.ui.imageloader.MediaImage
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.settings.model.Permission
import com.kmp.movieapp.settings.model.PermissionDemoResult
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsContent() {
    PermissionDemoScreen()
}


/**
 * This screen is only for demonstrating how you could ask for a
 * device feature and show up the results immediately after we
 * received the data without blocking the ui.
 */
@Composable
private fun PermissionDemoScreen() {
    val viewModel = koinViewModel<SettingsScreenViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.padding.sixteen),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.defaultContentPadding)
    ) {
        PermissionButton(
            label = stringResource(Res.string.permission_camera),
            onClick = {
                viewModel.onAction(SettingsAction.OnPermissionClicked(Permission.CAMERA))
            }
        )
        PermissionButton(
            label = stringResource(Res.string.permission_location),
            onClick = {
                viewModel.onAction(SettingsAction.OnPermissionClicked(Permission.LOCATION))
            }
        )
        PermissionButton(
            label = stringResource(Res.string.permission_microphone),
            onClick = {
                viewModel.onAction(SettingsAction.OnPermissionClicked(Permission.MICROPHONE))
            }
        )

        PermissionButton(
            label = stringResource(Res.string.permission_image_gallery),
            onClick = {
                viewModel.onAction(SettingsAction.OnPermissionClicked(Permission.GALLERY))
            }
        )

        // show results
        when (val result = state.permissionDemoResult) {
            is PermissionDemoResult.CameraReady -> {
                Text("Camera active!", color = MaterialTheme.colorScheme.onBackground)
            }

            is PermissionDemoResult.LocationReady -> {
                Text(
                    "Location: ${result.latitude}, ${result.longitude}",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            is PermissionDemoResult.MicrophoneReady -> {
                Text(
                    "Mikrophone active",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            is PermissionDemoResult.GalleryReady -> {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(result.mediaList) { media ->
                        MediaImage(
                            imageString = media.uri,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            is PermissionDemoResult.PermissionDenied ->
                Text(
                    text = "Permission denied: ${result.permission}",
                    color = MaterialTheme.colorScheme.onBackground
                )

            is PermissionDemoResult.PermissionPermanentlyDenied ->
                Text(
                    text = "Please allow the permission for: ${result.permission}",
                    color = MaterialTheme.colorScheme.onBackground
                )

            else -> Unit
        }
    }
}

@Composable
private fun PermissionButton(
    label: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label)
    }
}