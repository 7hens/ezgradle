package me.thens.ezgradle.config

import me.thens.ezgradle.model.EzGradleProperties
import me.thens.ezgradle.util.configure
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlatformExtension
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.hasPlugin

internal fun Project.configureJava() {
    if (plugins.hasPlugin(JavaPlugin::class)) {
        configure<JavaPluginExtension>("java") {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            sourceSets.getByName("main") {
                java.srcDir(EzGradleProperties.GENERATED_DIR)
            }
        }
    }
}

internal fun Project.configureJavaPlatform() {
    if (plugins.hasPlugin("java-platform")) {
        configure<JavaPlatformExtension>("javaPlatform") {
            allowDependencies()
        }
    }
}
