package io.github.podcastindex_sdk.gradle

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class SpotlessConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")

            val spotlessExtension = extensions.getByType<SpotlessExtension>()
            configureSpotless(spotlessExtension)
        }
    }

    private fun Project.configureSpotless(
        spotlessExtension: SpotlessExtension
    ) {
        spotlessExtension.apply {
            kotlin {
                target(listOf("*.md", "**/*.kt", ".gitignore", "**/*.gradle.kts"))
                ktlint()
                trimTrailingWhitespace()
                indentWithSpaces(4)
                endWithNewline()
            }
        }
    }
}