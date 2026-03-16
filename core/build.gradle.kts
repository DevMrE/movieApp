import org.jetbrains.kotlin.gradle.dsl.JvmTarget

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
        namespace = "com.kmp.movieapp.core"
        compileSdk = 36
        minSdk = 35

        // IMPORTANT for kmp module resources
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

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

            implementation(libs.kmpNavigation)
        }

        androidMain.dependencies {
            implementation(libs.ktorOkHttp)
            implementation(libs.android.conscrypt)
            implementation(libs.play.services.location)
        }

        iosMain.dependencies {
            implementation(libs.ktorDarwin)
        }

        jvmMain.dependencies {
            implementation(libs.ktorOkHttp)
        }
    }
}

compose.resources {
    packageOfResClass = "com.kmp.movieapp.core"
}