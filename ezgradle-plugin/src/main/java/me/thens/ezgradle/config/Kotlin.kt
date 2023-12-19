package me.thens.ezgradle.config

import me.thens.ezgradle.misc.configure
import me.thens.ezgradle.misc.kotlin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

internal fun Project.configureKotlin() {
    if (extensions.findByName("kotlin") != null) {
        configure<KotlinProjectExtension>("kotlin") {
            sourceSets.getByName("main") {
                kotlin.srcDir("build/generated/src/main/kotlin")
            }
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
