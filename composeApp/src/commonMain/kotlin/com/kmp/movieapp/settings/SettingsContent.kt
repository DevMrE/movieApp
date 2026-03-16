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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.core.util.permission.Permission
import com.kmp.movieapp.settings.model.PermissionDemoResult
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings")
    }
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
            label = "Kamera öffnen",
            onClick = {
                viewModel.onAction(SettingsAction.OnPermissionClicked(Permission.CAMERA))
            }
        )
        PermissionButton(
            label = "Standort abrufen",
            onClick = {
                viewModel.onAction(SettingsAction.OnPermissionClicked(Permission.LOCATION))
            }
        )
        PermissionButton(
            label = "Mikrofon aktivieren",
            onClick = {
                viewModel.onAction(SettingsAction.OnPermissionClicked(Permission.MICROPHONE))
            }
        )
        PermissionButton(
            label = "Benachrichtigungen erlauben",
            onClick = {
                viewModel.onAction(SettingsAction.OnPermissionClicked(Permission.NOTIFICATION))
            }
        )
        PermissionButton(
            label = "Galerie öffnen",
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
                Text("Standort: ${result.lat}, ${result.lng}")
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