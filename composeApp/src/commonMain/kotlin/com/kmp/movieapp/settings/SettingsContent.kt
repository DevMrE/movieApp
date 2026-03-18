package com.kmp.movieapp.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.permission.domain.Permission
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.settings.model.PermissionDemoResult
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.permission_camera
import movieapp.composeapp.generated.resources.permission_image_gallery
import movieapp.composeapp.generated.resources.permission_location
import movieapp.composeapp.generated.resources.permission_microphone
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsContent() {
    PermissionDemoScreen()
}


@Composable
private fun PermissionDemoScreen() {
    val viewModel = koinViewModel<SettingsScreenViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.padding.sixteen),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.ten)
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

        // Ergebnis anzeigen
        when (val result = state.permissionDemoResult) {
            is PermissionDemoResult.CameraReady -> {
                Text("Kamera bereit!")
            }

            is PermissionDemoResult.LocationReady -> {
                Text("Standort: ${result.latitude}, ${result.longitude}")
            }

            is PermissionDemoResult.MicrophoneReady -> {
                Text("️Mikrofon aktiv!")
            }

            is PermissionDemoResult.NotificationReady -> {
                Text("Benachrichtigungen aktiviert!")
            }

            is PermissionDemoResult.GalleryReady -> {
                Text("️Galerie geöffnet!")
            }

            is PermissionDemoResult.PermissionDenied -> {
                Text("Permission verweigert: ${result.permission}")
            }

            is PermissionDemoResult.PermissionPermanentlyDenied -> {
                Text("Bitte in Einstellungen erlauben: ${result.permission}")
            }

            null -> Unit
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