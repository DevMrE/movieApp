package com.kmp.movieapp.device_operations.di

import com.kmp.movieapp.device_operations.data.controller.DeviceOperationsControllerImpl
import com.kmp.movieapp.device_operations.data.mapper.toPermissionState
import com.kmp.movieapp.device_operations.data.permission.AndroidPermissionGateway
import com.kmp.movieapp.device_operations.data.permission.AndroidPermissionGatewayImpl
import com.kmp.movieapp.device_operations.data.provider.AndroidCameraProvider
import com.kmp.movieapp.device_operations.data.provider.AndroidGalleryProvider
import com.kmp.movieapp.device_operations.data.provider.AndroidLocationProvider
import com.kmp.movieapp.device_operations.domain.controller.DeviceOperationsController
import com.kmp.movieapp.device_operations.domain.provider.CameraProvider
import com.kmp.movieapp.device_operations.domain.provider.GalleryProvider
import com.kmp.movieapp.device_operations.domain.provider.LocationProvider
import com.kmp.movieapp.device_operations.platform.AndroidDeviceOperationsBinder
import kotlinx.coroutines.flow.map
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Provides Android-specific dependencies for device operations.
 */
actual val deviceModule: Module = module {

    single {
        AndroidCameraProvider(
            context = androidContext()
        )
    }

    single {
        AndroidGalleryProvider()
    }

    single {
        AndroidLocationProvider(
            context = androidContext()
        )
    }

    single<CameraProvider> { get<AndroidCameraProvider>() }
    single<GalleryProvider> { get<AndroidGalleryProvider>() }
    single<LocationProvider> { get<AndroidLocationProvider>() }

    single {
        AndroidDeviceOperationsBinder(
            androidCameraProvider = get(),
            androidGalleryProvider = get()
        )
    }

    single<AndroidPermissionGateway> {
        AndroidPermissionGatewayImpl(
            androidDeviceOperationsBinder = get()
        )
    }

    single<DeviceOperationsController> {
        DeviceOperationsControllerImpl(
            cameraPermission = {
                get<AndroidPermissionGateway>()
                    .requestCameraPermission()
                    .map { state -> state.toPermissionState() }
            },
            locationPermission = {
                get<AndroidPermissionGateway>()
                    .requestLocationPermission()
                    .map { state -> state.toPermissionState() }
            },
            cameraProvider = get(),
            galleryProvider = get(),
            locationProvider = get()
        )
    }
}