import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.spotless)
    alias(libs.plugins.vanniktech.maven)
}

val GROUP: String by project
group = GROUP

kotlin {
    explicitApi()
    jvmToolchain(libs.versions.jvmToolchain.get().toInt())

    val kotlinTargetVersion = libs.versions.kotlinTarget.map { KotlinVersion.fromVersion(it.toKotlinMinor()) }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        languageVersion = kotlinTargetVersion
        apiVersion = kotlinTargetVersion
        freeCompilerArgs.add(libs.versions.jdkTarget.map { "-Xjdk-release=${it.toJdkTarget()}" })
    }
    coreLibrariesVersion = libs.versions.kotlinTarget.get()

    jvm()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "podcastindex-sdk-ktor2"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.ktor2.core)
            implementation(libs.ktor2.content.negotiation)
            implementation(libs.ktor2.kotlinx.serialization)
            implementation(libs.ktor2.logging)
            implementation(libs.kermit)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.datetime)
            api(project(":podcastindex-sdk-base"))
        }

        jvmMain.dependencies {
            implementation(libs.ktor2.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor2.darwin)
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget = libs.versions.jdkTarget.map { JvmTarget.fromTarget(it.toJdkTarget()) }
    }
}

tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = libs.versions.jdkTarget.map { it.toJdkTarget() }.get()
    targetCompatibility = libs.versions.jdkTarget.map { it.toJdkTarget() }.get()
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

private fun String.toJdkTarget() = if (toInt() <= 8) "1.$this" else this

private fun String.toKotlinMinor() = split(".").take(2).joinToString(".")
