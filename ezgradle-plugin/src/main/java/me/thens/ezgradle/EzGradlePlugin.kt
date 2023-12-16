package me.thens.ezgradle

import me.thens.ezgradle.config.configureAndroidApplication
import me.thens.ezgradle.config.configureAndroidLibrary
import me.thens.ezgradle.config.configureHilt
import me.thens.ezgradle.config.configureJava
import me.thens.ezgradle.config.configureKapt
import me.thens.ezgradle.config.configureKotlinAndroid
import me.thens.ezgradle.config.configureMavenPublish
import me.thens.ezgradle.lib.platform
import me.thens.ezgradle.lib.resolveVersions
import me.thens.ezgradle.misc.extra
import me.thens.ezgradle.misc.loadProperties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.extra
import java.io.File

class EzGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.configure()
    }

    private fun Project.configure() {
        loadProperties("gradle.properties")
        loadProperties("local.properties")
        group = extra("PACKAGE_NAME")
        version = extra("VERSION")
        configureJava()
        configureAndroidApplication()
        configureAndroidLibrary()
        configureKotlinAndroid()
        configureKapt()
        configureHilt()
        configureMavenPublish()
        if (configurations.findByName("implementation") != null) {
            dependencies {
                platform()
                resolveVersions(File(rootProject.rootDir, "gradle/libs.versions.txt"))
            }
        }
    }
}