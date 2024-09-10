plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.app)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.mr3y.podcastindex.sample"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        applicationId = "com.mr3y.podcastindex.sample"
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    dependencies {
        implementation(project(":sample:common"))

        implementation(libs.androidx.activity.compose)
    }
}