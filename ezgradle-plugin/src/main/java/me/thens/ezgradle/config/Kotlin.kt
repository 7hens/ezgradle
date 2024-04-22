package me.thens.ezgradle.config

import me.thens.ezgradle.util.configure
import me.thens.ezgradle.util.kotlin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

internal fun Project.configureKotlin() {
    if (extensions.findByName("kotlin") != null) {
        configure<KotlinProjectExtension>("kotlin") {
            sourceSets.getByName("main") {
//                kotlin.srcDir("build/generated/source/ezgradle/main/java")
            }
            jvmToolchain(17)
        }
    }
}

internal fun Project.configureKapt() {
    if (plugins.hasPlugin(kotlin("kapt"))) {
        configure<KaptExtension>("kapt") {
            correctErrorTypes = true
            useBuildCache = true
        }
    }
}
