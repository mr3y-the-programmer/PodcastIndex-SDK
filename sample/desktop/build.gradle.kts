import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(17)
    jvm()

    sourceSets.jvmMain.dependencies {
        implementation(project(":sample:common"))

        implementation(compose.desktop.currentOs)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.mr3y.podcastindex.sample"
            packageVersion = "1.0.0"
        }
    }
}