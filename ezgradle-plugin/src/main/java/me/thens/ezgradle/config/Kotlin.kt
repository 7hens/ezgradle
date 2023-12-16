package me.thens.ezgradle.config

import me.thens.ezgradle.misc.configure
import me.thens.ezgradle.misc.kotlin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

internal fun Project.configureKotlinAndroid() {
    if (plugins.hasPlugin(kotlin("android"))) {
        configure<KotlinAndroidProjectExtension>("kotlin") {
            jvmToolchain(17)
        }
    }
}

internal fun Project.configureKapt() {
    if (plugins.hasPlugin(kotlin("kapt"))) {
        configure<KaptExtension>("kapt") {
            correctErrorTypes = true
        }
    }
}
