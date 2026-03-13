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

    androidLibrary {
        namespace = "com.kmp.movieapp.search"
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
                // Add KMP dependencies here

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

            }
        }


        iosMain {
            dependencies {

            }
        }
    }
}

compose.resources {
    packageOfResClass = "com.kmp.movieapp.search"
}