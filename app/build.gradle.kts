plugins {
    alias(libs.plugins.android.application) // Đảm bảo bạn đã cấu hình `libs` trong `libs.versions.toml`
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {
    // Các thư viện AndroidX
    implementation("androidx.appcompat:appcompat:1.3.1") // AppCompat
    implementation("com.google.android.material:material:1.4.0") // Material Design
    implementation("androidx.activity:activity:1.2.4") // Activity
    implementation("androidx.constraintlayout:constraintlayout:2.1.0") // ConstraintLayout

    // Thư viện testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // Các thư viện bổ sung
    implementation("com.google.code.gson:gson:2.8.8") // Gson
    implementation("androidx.cardview:cardview:1.0.0") // CardView
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Retrofit Gson Converter
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0") // OkHttp logging interceptor
    implementation ("com.google.android.material:material:1.6.0")
    // Glide cho image loading
    implementation("com.github.bumptech.glide:glide:4.15.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.0") // Glide compiler
    implementation ("com.squareup.picasso:picasso:2.71828")
}
