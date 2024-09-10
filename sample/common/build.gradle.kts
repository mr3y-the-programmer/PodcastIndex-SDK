import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    jvmToolchain(17)
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(project(":lib"))
        }
    }

}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}


android {
    namespace = "com.mr3y.podcastindex.sample"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
