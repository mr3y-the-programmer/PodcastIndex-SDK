plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.poko)
    alias(libs.plugins.spotless)
    id("convention.publication")
}

group = "com.mr3y.podcastindex"
version = "1.0"

kotlin {
    explicitApi()
    jvmToolchain(17)

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.core)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.kotlinx.serialization)
            implementation(libs.ktor.logging)
            implementation(libs.kermit)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.datetime)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.okhttp)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        target(listOf("*.md", "**/*.kt", ".gitignore", "**/*.gradle.kts"))
        ktlint()
        trimTrailingWhitespace()
        indentWithSpaces(4)
        endWithNewline()
    }
}
