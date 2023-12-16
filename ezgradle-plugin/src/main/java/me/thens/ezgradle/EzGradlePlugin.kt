package me.thens.ezgradle

import me.thens.ezgradle.config.configureAndroidApplication
import me.thens.ezgradle.config.configureAndroidLibrary
import me.thens.ezgradle.config.configureHilt
import me.thens.ezgradle.config.configureKapt
import me.thens.ezgradle.config.configureKotlinAndroid
import me.thens.ezgradle.lib.platform
import me.thens.ezgradle.lib.resolveVersions
import me.thens.ezgradle.misc.loadProperties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import java.io.File

class EzGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.configure()
    }

    private fun Project.configure() {
        loadProperties("gradle.properties")
        loadProperties("local.properties")
        configureAndroidApplication()
        configureAndroidLibrary()
        configureKotlinAndroid()
        configureKapt()
        configureHilt()
        dependencies {
            platform()
            resolveVersions(File(rootProject.rootDir, "gradle/libs.versions.txt"))
        }
    }
}