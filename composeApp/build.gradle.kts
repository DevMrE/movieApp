@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.serialization)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }

    // Desktop - Windows + macOS
    jvm("desktop")

    // Android
    androidTarget()

    // iOS (Framework + XCFramework)
    val frameworkName = "ComposeApp"
    val xcf = XCFramework(frameworkName)

    val basePackage = getPropertyString("app.basePackagePath")
    val frameworkBundleId = "$basePackage.${frameworkName.lowercase()}"

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
        // iosX64(),
    ).forEach { target ->
        target.binaries.framework {
            baseName = frameworkName

            // CFBundleIdentifier im Framework-Info.plist
            binaryOption("bundleId", frameworkBundleId)

            // Wenn Swift auch APIs von exportierten Dependencies sehen soll:
            // export(project(":core"))
            transitiveExport = true

            isStatic = false
            xcf.add(this)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)

            implementation(libs.bundles.commonMainCompose)
            implementation(libs.savedState)
            implementation(libs.window.core)

            implementation(libs.bundles.lifecycle)
            implementation(libs.bundles.commainMainKoin)

            implementation(libs.logger)

            // kmp navigation
            implementation(libs.kmpNavigation)

            implementation(project(":core"))
            implementation(project(":movie"))
            implementation(project(":series"))
            implementation(project(":detail"))
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.android.conscrypt)
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