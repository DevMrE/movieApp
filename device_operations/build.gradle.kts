plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.serialization)
}

kotlin {

    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }

    androidLibrary {
        namespace = "com.kmp.movieapp.device_operations"
        compileSdk = 36
        minSdk = 35

        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    // Desktop - Windows + MacOS
    jvm("desktop")

    // iOS -> iPhone + iPad implementation
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)

                implementation(libs.kotlin.stdlib)
                implementation(libs.coroutines.core)
                implementation(libs.koinCore)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.coroutines.android)
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.core.ktx.main)
                implementation(libs.play.services.location)
                implementation(libs.koinAndroid)
            }
        }

        iosMain {
            dependencies {

            }
        }
    }
}