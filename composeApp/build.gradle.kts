@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.serialization)
}

kotlin {

    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }

    // Desktop - Windows + MacOS
    jvm("desktop")

    // Android implementation
    androidTarget()

    // iOS -> iPhone + iPad implementation
    iosArm64()
    iosSimulatorArm64()

    // iOS Framework config
    cocoapods {
        val moduleName = "ComposeApp"
        version = getPropertyString("app.version")
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"

        name = moduleName

        ios.deploymentTarget = getPropertyString("ios.deploymentTarget")

        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = moduleName/*
             * Do not confuse this static with the one from the Podfile.
             * In the Podfile, we define whether the Podfile aggregator
             * itself should be static or dynamic. Here, we define whether
             * the ComposeApp framework should be passed dynamically to CocoaPods.
             * In short, we want ComposeApp to be passed dynamically,
             * but the PodAggregator should be static.
             */
            isStatic = false
            transitiveExport = true
        }

        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.bundles.commainMainKoin)

            implementation(project(":core"))
            implementation(project(":navigation"))
            implementation(project(":movie"))
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }

        // iOS
        iosMain.dependencies {

        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

    }
}

android {
    namespace = getPropertyString("app.basePackagePath")
    compileSdk = getPropertyInt("android.compileSdk")

    defaultConfig {
        applicationId = getPropertyString("app.basePackagePath")

        minSdk = getPropertyInt("android.mobile.minSdk")
        targetSdk = getPropertyInt("android.mobile.targedSdk")

        versionCode = 1
        versionName = getPropertyString("app.version")
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg, // macOS
                TargetFormat.Msi  // Windows
            )
        }
    }
}

fun getPropertyString(string: String): String {
    return providers.gradleProperty(string).get()
}

fun getPropertyInt(string: String): Int {
    return providers.gradleProperty(string).get().toInt()
}