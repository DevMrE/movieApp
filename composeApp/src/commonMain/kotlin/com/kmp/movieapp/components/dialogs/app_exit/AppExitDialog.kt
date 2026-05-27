package com.kmp.movieapp.components.dialogs.app_exit

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import com.kmp.movieapp.core.util.navigation.util.koinNavigation

@Composable
fun AppExitDialog() {
    val navigator: Navigator<AppNavigation> = koinNavigation()
    var showDialog by rememberSaveable { mutableStateOf(true) }

    if (showDialog)
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        navigator.navigateBack()
                    }
                ) {
                    Text("Yes, that is an horrible App!")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("No I wanna stay in this cool App!")
                }
            },
            title = {
                Text("Do you really want to exit?")
            }
        )
}