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

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)

                implementation(libs.logger)

                // Add KMP dependencies here
                implementation(libs.bundles.commainMainKoin)
                implementation(libs.bundles.commonMainKtor)
                implementation(project(":core"))
                implementation(project(":navigation"))
            }
        }

        androidMain {
            dependencies {
                implementation("org.slf4j:slf4j-simple:2.0.16")
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