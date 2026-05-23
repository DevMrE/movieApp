plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
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
        namespace = "${getPropertyString("app.basePackagePath")}.navigation"
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
                // Add KMP dependencies here
                implementation(libs.bundles.commonMainCompose)
                implementation(libs.savedState)
                implementation(libs.window.core)
                implementation(libs.bundles.lifecycle)
                implementation(libs.bundles.commainMainKoin)
                implementation(libs.bundles.composeNavigation3)

                // Modules
                implementation(project(":core"))
                implementation(project(":content_detail"))
                implementation(project(":search"))
                implementation(project(":discover"))
                implementation(project(":device_operations"))
                implementation(project(":movie"))
                implementation(project(":series"))
                implementation(project(":trending"))
                implementation(project(":overview_list"))
            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
            }
        }

        iosMain {
            dependencies {

            }
        }
    }
}

compose.resources {
    packageOfResClass = "com.kmp.movieapp.navigation"
}


fun getPropertyString(string: String): String {
    return providers.gradleProperty(string).get()
}

fun getPropertyInt(string: String): Int {
    return providers.gradleProperty(string).get().toInt()
}