import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.beatrice.popularmovies"
    compileSdk = 33

    defaultConfig {
        minSdk = 22
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0-alpha02"
    }
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":data")))

    val composeVersion = "1.4.0-alpha03"
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")

    // Material 3
    implementation("androidx.compose.material3:material3:1.1.0-alpha04")

    // Constraint Layout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Navigation
    val navVersion = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // Lifecycle
    val lifecycleVersion = "2.6.0-alpha03"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-compiler:2.44.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")


    // logcat
    implementation("com.squareup.logcat:logcat:0.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")

    // Coroutines
    val coroutineVersion = "1.6.4"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
}

kapt {
    correctErrorTypes = true
}