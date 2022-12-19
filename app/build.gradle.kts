plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
//    id 'kotlin-android-extensions'
    id("kotlin-parcelize")
}

android {
    compileSdk = 31
    buildToolsVersion ="30.0.3"

    defaultConfig {
        applicationId= "ru.lachesis.translator"
        minSdk =22
        targetSdk =31
        versionCode = 1
        versionName ="1.0"

        testInstrumentationRunner =  "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles( getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility= JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding =  true
    }

}

dependencies {

    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    /*  //retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    implementation "com.squareup.retrofit2:adapter-rxjava2:2.9.0"
*/
    //Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("com.squareup.retrofit2:converter-gson:2.6.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.12.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    //rxJava
//    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
//    implementation 'io.reactivex.rxjava3:rxjava:3.0.0'
//    implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
//    implementation 'io.reactivex.rxjava2:rxjava:2.2.8'
//    //rsKotlin
//    implementation("io.reactivex.rxjava3:rxkotlin:3.0.0")

    //Koin for Android
    //Current version
    val koin_version= "3.1.2"
    //Koin core features
    implementation ("io.insert-koin:koin-core:$koin_version")
    //Koin main features for Android (Scope,ViewModel ...)
    implementation("io.insert-koin:koin-android:$koin_version")
    //Koin Java Compatibility
    implementation("io.insert-koin:koin-android-compat:$koin_version")

    //Download images
    //Coil
    implementation ("io.coil-kt:coil:0.11.0")

    //Room
    implementation("androidx.room:room-runtime:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")
    implementation("androidx.room:room-ktx:2.3.0")

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}