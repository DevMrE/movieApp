plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.serialization)
}

kotlin {

    compilerOptions {
        freeCompilerArgs.add(getPropertyString("compiler.feature.context"))
    }

    android {
        // Use a unique namespace to avoid collisions with the androidApp module
        namespace = "${getPropertyString("app.basePackagePath")}.content_detail"
        compileSdk = getPropertyInt("android.compileSdk")
        minSdk = getPropertyInt("android.mobile.minSdk")

        androidResources {
            enable = true
        }
    }

    jvm()

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

                implementation(libs.bundles.commainMainKoin)
                implementation(libs.bundles.commonMainKtor)

                // kmp navigation
                implementation(libs.kmpNavigation)

                implementation(project(":core"))
                implementation(project(":movie"))
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

dependencies {
    androidRuntimeClasspath(libs.composeTooling)
}

compose.resources {
    packageOfResClass = "${getPropertyString("app.basePackagePath")}.content_detail"
}

fun getPropertyString(string: String): String {
    return providers.gradleProperty(string).get()
}

fun getPropertyInt(string: String): Int {
    return providers.gradleProperty(string).get().toInt()
}