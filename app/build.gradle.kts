plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.catphotos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.catphotos"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //Livedata viewmodel
    val lifecycle_version = "2.5.0-alpha02"

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    val fragment_version = "1.5.5"

    // Fragment KTX
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    //Pesssoais
    //Retrofit + Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Imagens Picasso
    implementation("com.squareup.picasso:picasso:2.8")

    // For Robolectric tests Hilt.
    testImplementation("com.google.dagger:hilt-android-testing:2.48")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:2.48")
    testImplementation("junit:junit:4.13.2")
    //Truth
    testImplementation("com.google.truth:truth:1.3.0")
    //Arch Core -- para testar livedata
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    //Coroutines Testes Jetbrains
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0-RC2")
    //Mockito
    testImplementation("org.mockito:mockito-core:5.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}