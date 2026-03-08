plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
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
        namespace = "com.kmp.movieapp.content_detail"
        compileSdk = 36
        minSdk = 24

        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate

    // iOS -> iPhone + iPad implementation
    iosArm64()
    iosSimulatorArm64()

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
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

            }
        }

        iosMain {
            dependencies {

            }
        }
    }
}

compose.resources {
    packageOfResClass = "com.kmp.movieapp.content_detail"
}