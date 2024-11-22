package io.github.podcastindex_sdk.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

class CompatibilityConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")
            pluginManager.apply("dev.drewhamilton.poko")

            val kotlinExtension = extensions.getByType<KotlinMultiplatformExtension>()
            configureKotlinExtension(kotlinExtension)
        }
    }

    private fun Project.configureKotlinExtension(
        kotlinExtension: KotlinMultiplatformExtension
    ) {
        kotlinExtension.apply {
            explicitApi()
            jvmToolchain(libs.findVersion("jvmToolchain").get().toString().toInt())

            jvm()
            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64(),
            ).forEach {
                it.binaries.framework {
                    baseName = when (this@configureKotlinExtension.name) {
                        "podcastindex-sdk-base" -> "podcastindex-sdk"
                        "podcastindex-ktor2" -> "podcastindex-sdk-ktor2"
                        "podcastindex-ktor3" -> "podcastindex-sdk-ktor3"
                        else -> error("Unknown project name: ${this@configureKotlinExtension.name}")
                    }
                    isStatic = true
                }
            }

            val kotlinTargetVersion = KotlinVersion.fromVersion(libs.findVersion("kotlinTarget").get().toString().toKotlinMinor())

            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            compilerOptions {
                languageVersion.set(kotlinTargetVersion)
                apiVersion.set(kotlinTargetVersion)
                freeCompilerArgs.add(libs.findVersion("jdkTarget").get().toString().let { "-Xjdk-release=${it.toJdkTarget()}" })
            }
            coreLibrariesVersion = libs.findVersion("kotlinTarget").get().toString()
        }

        configureCompilationTasks()
    }

    private fun Project.configureCompilationTasks() {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.fromTarget(libs.findVersion("jdkTarget").get().toString().toJdkTarget()))
            }
        }

        tasks.withType<JavaCompile>().configureEach {
            sourceCompatibility = libs.findVersion("jdkTarget").get().toString().toJdkTarget()
            targetCompatibility = libs.findVersion("jdkTarget").get().toString().toJdkTarget()
        }
    }

    private fun String.toJdkTarget() = if (toInt() <= 8) "1.$this" else this

    private fun String.toKotlinMinor() = split(".").take(2).joinToString(".")
}
