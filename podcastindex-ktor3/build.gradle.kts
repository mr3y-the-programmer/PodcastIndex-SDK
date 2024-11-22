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
            api(libs.ktor3.core)
            implementation(libs.ktor3.content.negotiation)
            implementation(libs.ktor3.kotlinx.serialization)
            implementation(libs.ktor3.logging)
            implementation(libs.kermit)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.datetime)
            api(projects.podcastindexSdkBase)
        }

        jvmMain.dependencies {
            implementation(libs.ktor3.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor3.darwin)
        }
    }
}
