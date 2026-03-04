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
        namespace = "com.kmp.movieapp.detail"
        compileSdk = 36
        minSdk = 35

        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true

    }

    jvm("desktop")

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
    packageOfResClass = "com.kmp.movieapp.detail"
}