plugins {
    id "com.android.application"
    id "org.jetbrains.kotlin.android"
    id "kotlin-kapt"
    id "com.google.dagger.hilt.android"
    id "kotlin-parcelize"
}

android {
    namespace "com.beatrice.moviesapp"
    compileSdk 33

    defaultConfig {
        applicationId "com.beatrice.moviesapp"
        minSdk 21
        targetSdk 33
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary true
        }
    }

    buildTypes {
        release {
            buildConfigField "String", "BASE_URL", "\"https://api.themoviedb.org/3/\""

            minifyEnabled false
            proguardFiles getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
        }
        debug {
            buildConfigField "String", "BASE_URL", "\"https://api.themoviedb.org/3/\""
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion "1.4.0-alpha02"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.activity:activity-compose:1.6.1")
    implementation ("androidx.compose.ui:ui:$compose_version")
    implementation ("androidx.compose.ui:ui-tooling-preview:$compose_version")


    // Lifecycle
    def lifecycle_version = "2.6.0-alpha03")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

    // Material 3
    implementation ("androidx.compose.material3:material3:1.1.0-alpha03")

    // Constraint Layout
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Navigation
    def nav_version = "2.5.3"
    implementation ("androidx.navigation:navigation-compose:$nav_version")

    // Room
    def room_version = "2.4.3"
    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    //Moshi
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    //OkHttp3
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.10")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Hilt
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    // logcat
    implementation ("com.squareup.logcat:logcat:0.1")

    // Coil
    implementation ("io.coil-kt:coil-compose:2.2.2")

    // Test
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.4")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.0")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation ("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:$compose_version")
}

kapt {
    correctErrorTypes = true
}