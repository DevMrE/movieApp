@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.serialization)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add(getPropertyString("compiler.feature.context"))
    }

    // Desktop - Windows + macOS
    jvm()

    // Android
    android {
        // Use a unique namespace to avoid collisions with the androidApp module
        namespace = "${getPropertyString("app.basePackagePath")}.composeApp"
        compileSdk = getPropertyInt("android.compileSdk")
        minSdk = getPropertyInt("android.mobile.minSdk")

        androidResources {
            enable = true
        }
    }
    
    // iOS (Framework + XCFramework)
    val frameworkName = "ComposeApp"
    val xcf = XCFramework(frameworkName)

    val basePackage = getPropertyString("app.basePackagePath")
    val frameworkBundleId = "$basePackage.${frameworkName.lowercase()}"

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { target ->
        target.binaries.framework {
            baseName = frameworkName
            binaryOption("bundleId", frameworkBundleId)
            transitiveExport = true
            isStatic = true
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
            implementation(libs.kmpNavigation)

            implementation(project(":core"))
            implementation(project(":content_detail"))
            implementation(project(":search"))
            implementation(project(":discover"))
            implementation(project(":device_operations"))
            implementation(project(":features"))
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.android.conscrypt)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
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

compose.resources {
    // Explicitly set the package name for the generated Res class
    packageOfResClass = "com.kmp.movieapp.composeApp"
}

fun getPropertyString(string: String): String {
    return providers.gradleProperty(string).get()
}

fun getPropertyInt(string: String): Int {
    return providers.gradleProperty(string).get().toInt()
}