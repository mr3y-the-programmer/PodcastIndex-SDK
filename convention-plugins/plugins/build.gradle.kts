import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl` // Is needed to turn our build logic written in Kotlin into Gradle Plugin
}

dependencies {
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.spotless.gradle)
}

gradlePlugin {
    plugins {
        register("compatibilityConventions") {
            id = "conventions.compatibility"
            implementationClass = "io.github.podcastindex_sdk.gradle.CompatibilityConventionPlugin"
        }
        register("spotlessConventions") {
            id = "conventions.spotless"
            implementationClass = "io.github.podcastindex_sdk.gradle.SpotlessConventionPlugin"
        }
    }
}
