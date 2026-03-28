package com.kmp.movieapp.device_operations.di

import com.kmp.movieapp.device_operations.data.controller.DeviceOperationsControllerImpl
import com.kmp.movieapp.device_operations.data.permission.IOSPermissionGateway
import com.kmp.movieapp.device_operations.data.permission.IOSPermissionGatewayImpl
import com.kmp.movieapp.device_operations.data.provider.IOSCameraProvider
import com.kmp.movieapp.device_operations.data.provider.IOSGalleryProvider
import com.kmp.movieapp.device_operations.data.provider.IOSLocationProvider
import com.kmp.movieapp.device_operations.domain.controller.DeviceOperationsController
import com.kmp.movieapp.device_operations.domain.provider.CameraProvider
import com.kmp.movieapp.device_operations.domain.provider.GalleryProvider
import com.kmp.movieapp.device_operations.domain.provider.LocationProvider
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Provides iOS-specific dependencies for device operations.
 */
actual fun deviceModule(): Module = module {

    single<IOSPermissionGateway> {
        IOSPermissionGatewayImpl()
    }

    single {
        IOSCameraProvider()
    }

    single {
        IOSGalleryProvider(
            iosDeviceOperationsBinder = get()
        )
    }

    single {
        IOSLocationProvider()
    }

    single<CameraProvider> { get<IOSCameraProvider>() }
    single<GalleryProvider> { get<IOSGalleryProvider>() }
    single<LocationProvider> { get<IOSLocationProvider>() }

    single<DeviceOperationsController> {
        DeviceOperationsControllerImpl(
            cameraPermission = {
                get<IOSPermissionGateway>().requestCameraPermission()
            },
            locationPermission = {
                get<IOSPermissionGateway>().requestLocationPermission()
            },
            cameraProvider = get(),
            galleryProvider = get(),
            locationProvider = get()
        )
    }
}