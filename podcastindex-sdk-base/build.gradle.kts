plugins {
    alias(libs.plugins.compatibility.conventions)
    alias(libs.plugins.spotless.conventions)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.vanniktech.maven)
}

val GROUP: String by project
group = GROUP

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.ktor2.core)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.datetime)
        }

        jvmMain.dependencies {
            implementation(libs.ktor2.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor2.darwin)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
