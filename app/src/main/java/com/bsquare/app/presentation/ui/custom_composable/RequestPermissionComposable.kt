package com.bsquare.app.presentation.ui.custom_composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import com.google.accompanist.permissions.MultiplePermissionsState
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestPermissionComposable(
    onPermissionGranted: () -> Unit,
    exactLocationPermissionNotGranted: () -> Unit,
    showRationale: () -> Unit,
    deniedPermissionForever: () -> Unit,
):  MultiplePermissionsState {

    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        onPermissionsResult = {
            if(it.isNotEmpty()) {
                when {
                    it.values.first() && it.values.last() -> {
                        onPermissionGranted()
                    }
                    !it.values.first() && it.values.last() -> {
                        showRationale()
                    }
                    it.values.first() && !it.values.last() -> {
                        showRationale()
                    }
                    else -> {
                        deniedPermissionForever()
                    }
                }
            }
        }
    )

    val lifeCycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifeCycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when(event) {
                Lifecycle.Event.ON_START -> {
                    if(!locationPermissionState.allPermissionsGranted) {

                        locationPermissionState.launchMultiplePermissionRequest()
                    }
                }
                Lifecycle.Event.ON_RESUME -> {
                    when {
                        locationPermissionState.allPermissionsGranted -> {
                            onPermissionGranted()
                        }
                        locationPermissionState.permissions.size != locationPermissionState.revokedPermissions.size -> {
                            exactLocationPermissionNotGranted();
                        }
                        locationPermissionState.shouldShowRationale -> {
                            showRationale()
                        }
                        !locationPermissionState.allPermissionsGranted && !locationPermissionState.shouldShowRationale -> {
                            deniedPermissionForever()
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }



    return locationPermissionState
}