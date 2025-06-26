@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace   = "com.example.mobileapplicationdevelopment2025"
    compileSdk  = 35

    defaultConfig {
        applicationId = "com.example.mobileapplicationdevelopment2025"
        minSdk        = 24
        targetSdk     = 34
        versionCode   = 1
        versionName   = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
        buildConfigField(
            "String",
            "CALORIE_API_KEY",
            "\"ggC/uB7RrxJHXRPAY5Flpg==Mhj07mvygaRs8pRe\""
        )
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }

    buildFeatures {
        compose     = true
        buildConfig = true       // enables BuildConfig fields
    }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.14" }

    packaging {
        resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }
    }
}

kapt { correctErrorTypes = true }


dependencies {

    implementation(libs.androidx.hilt.common)
    implementation(libs.androidx.hilt.work)
    /* ---------- Compose BOM ---------- */
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    /* ---------- Core & Lifecycle ---------- */
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("io.coil-kt:coil-compose:2.2.2")

    /* ---------- Jetpack Compose UI ---------- */
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.material3:material3:1.2.1")


    /* ---------- Navigation ---------- */
    implementation("androidx.navigation:navigation-compose:2.8.5")

    /* ---------- ConstraintLayout for Compose ---------- */
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    /* ---------- WorkManager ---------- */
    implementation("androidx.work:work-runtime-ktx:2.10.0")

    /* ---------- Room ---------- */
    val room = "2.6.1"
    implementation("androidx.room:room-runtime:$room")
    kapt("androidx.room:room-compiler:$room")
    implementation("androidx.room:room-ktx:$room")
    implementation("androidx.room:room-paging:$room")
    testImplementation("androidx.room:room-testing:$room")

    /* ---------- Paging ---------- */
    implementation("androidx.paging:paging-runtime-ktx:3.3.5")
    implementation("androidx.paging:paging-compose:3.3.5")

    /* ---------- Networking (Retrofit / OkHttp) ---------- */
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    /* ---------- Glide (classic + Compose) ---------- */
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    /* ---------- Hilt (DI) ---------- */
    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-android-compiler:2.51")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    /* ---------- Google Material ---------- */
    implementation("com.google.android.material:material:1.12.0")

    /* ---------- RxJava (if still needed elsewhere) ---------- */
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")

    /* ---------- Testing ---------- */
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.fragment:fragment-testing:1.8.5")
}
