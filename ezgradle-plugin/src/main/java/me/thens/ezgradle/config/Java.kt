package me.thens.ezgradle.config

import me.thens.ezgradle.misc.configure
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.hasPlugin

internal fun Project.configureJava() {
    if (plugins.hasPlugin(JavaPlugin::class)) {
        configure<JavaPluginExtension>("java") {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}
