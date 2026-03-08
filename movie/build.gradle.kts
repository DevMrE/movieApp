plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.serialization)
}

kotlin {

    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.kmp.movieapp.movie"
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

                implementation(libs.bundles.commonMainCompose)

                implementation(libs.savedState)
                implementation(libs.window.core)

                implementation(libs.bundles.lifecycle)

                implementation(libs.logger)

                // Add KMP dependencies here
                implementation(libs.bundles.commainMainKoin)
                implementation(libs.bundles.commonMainKtor)

                // kmp navigation
                implementation(libs.kmpNavigation)

                implementation(project(":core"))
            }
        }

        androidMain {
            dependencies {
                implementation(libs.slf4j)
            }
        }

        iosMain {
            dependencies {
            }
        }
    }
}

compose.resources {
    packageOfResClass = "com.kmp.movieapp.movie"
}