plugins {
    alias(libs.plugins.compatibility.conventions)
    alias(libs.plugins.spotless.conventions)
    alias(libs.plugins.vanniktech.maven)
}

val GROUP: String by project
group = GROUP

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.ktor2.core)
            implementation(libs.ktor2.content.negotiation)
            implementation(libs.ktor2.kotlinx.serialization)
            implementation(libs.ktor2.logging)
            implementation(libs.kermit)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.datetime)
            api(projects.podcastindexSdkBase)
        }

        jvmMain.dependencies {
            implementation(libs.ktor2.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor2.darwin)
        }
    }
}
