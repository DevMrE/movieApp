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

    android {
        // Use a unique namespace to avoid collisions with the androidApp module
        namespace = "${getPropertyString("app.basePackagePath")}.core"
        compileSdk = getPropertyInt("android.compileSdk")
        minSdk = getPropertyInt("android.mobile.minSdk")

        androidResources {
            enable = true
        }
    }


    jvm()

    // iOS -> iPhone + iPad implementation
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)

            implementation(libs.bundles.commonMainCompose)

            implementation(libs.logger)

            implementation(libs.bundles.commainMainKoin)

            implementation(libs.bundles.commonMainKtor)

            implementation(libs.kamel)

            implementation(libs.bundles.composeNavigation3)
        }

        androidMain.dependencies {
            implementation(libs.ktorOkHttp)
            implementation(libs.android.conscrypt)
            implementation(libs.play.services.location)
            implementation(libs.androidx.lifecycle.process)
            implementation(libs.composeTooling)
        }

        iosMain.dependencies {
            implementation(libs.ktorDarwin)
            implementation(libs.composeUi)
        }

        jvmMain.dependencies {
            implementation(libs.ktorOkHttp)
            implementation(libs.composeTooling)
        }
    }
}

compose.resources {
    packageOfResClass = "com.kmp.movieapp.core"
}

fun getPropertyString(string: String): String {
    return providers.gradleProperty(string).get()
}

fun getPropertyInt(string: String): Int {
    return providers.gradleProperty(string).get().toInt()
}