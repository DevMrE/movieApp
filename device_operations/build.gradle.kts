plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.serialization)
}

kotlin {

    compilerOptions {
        freeCompilerArgs.add(getPropertyString("compiler.feature.context"))
    }

    // Android
    android {
        // Use a unique namespace to avoid collisions with the androidApp module
        namespace = "${getPropertyString("app.basePackagePath")}.device_operations"
        compileSdk = getPropertyInt("android.compileSdk")
        minSdk = getPropertyInt("android.mobile.minSdk")

        androidResources {
            enable = true
        }
    }

    // Desktop - Windows + MacOS
    jvm()

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


fun getPropertyString(string: String): String {
    return providers.gradleProperty(string).get()
}

fun getPropertyInt(string: String): Int {
    return providers.gradleProperty(string).get().toInt()
}