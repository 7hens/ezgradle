package me.thens.ezgradle.config

import me.thens.ezgradle.ext
import me.thens.ezgradle.kotlin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

internal fun Project.configureKotlinAndroid() {
    if (plugins.hasPlugin(kotlin("android"))) {
        ext<KotlinAndroidProjectExtension>("kotlin") {
            jvmToolchain(17)
        }
    }
}

internal fun Project.configureKapt() {
    if (plugins.hasPlugin(kotlin("kapt"))) {
        ext<KaptExtension>("kapt") {
            correctErrorTypes = true
        }
    }
}
