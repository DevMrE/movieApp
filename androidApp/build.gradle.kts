plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinAndroid)
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

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(libs.androidx.core.ktx.main)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)

    implementation(libs.bundles.androidCompose)

    implementation(libs.bundles.commainMainKoin)
    implementation(libs.android.conscrypt)

    implementation(project(":composeApp"))
    implementation(project(":device_operations"))
}

dependencies {
    implementation(libs.androidx.core.ktx.main)
    debugImplementation(libs.composePreview)
}

fun getPropertyString(string: String): String {
    return providers.gradleProperty(string).get()
}

fun getPropertyInt(string: String): Int {
    return providers.gradleProperty(string).get().toInt()
}