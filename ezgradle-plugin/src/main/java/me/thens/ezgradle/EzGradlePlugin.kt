package me.thens.ezgradle

import me.thens.ezgradle.config.configureAndroidApplication
import me.thens.ezgradle.config.configureAndroidLibrary
import me.thens.ezgradle.config.configureHilt
import me.thens.ezgradle.config.configureJava
import me.thens.ezgradle.config.configureKapt
import me.thens.ezgradle.config.configureKotlinAndroid
import me.thens.ezgradle.config.configureMavenPublish
import me.thens.ezgradle.lib.applyAndroidConstraints
import me.thens.ezgradle.misc.extra
import me.thens.ezgradle.misc.isAndroid
import me.thens.ezgradle.misc.loadProperties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import java.io.File
import java.util.concurrent.TimeUnit

class EzGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.configure()
    }

    private fun Project.configure() {
//        pluginManager.apply(kotlin("kapt"))
        configurations.all {
            resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
        }
        loadProperties("gradle.properties")
        loadProperties("local.properties")
        group = extra("GROUP", "dev." + rootProject.name.lowercase())
        version = extra("VERSION", "1.0.0")
        configureJava()
        configureAndroidApplication()
        configureAndroidLibrary()
        configureKotlinAndroid()
        configureKapt()
        configureHilt()
        configureMavenPublish()
        if (isAndroid) {
            dependencies {
                applyAndroidConstraints()
            }
        }
    }
}