package me.thens.ezgradle

import dev.horizona.core.log.LogFilter
import me.thens.ezgradle.config.BuildConfigGenerator
import me.thens.ezgradle.config.configureAndroidApplication
import me.thens.ezgradle.config.configureAndroidLibrary
import me.thens.ezgradle.config.configureHilt
import me.thens.ezgradle.config.configureJava
import me.thens.ezgradle.config.configureJavaPlatform
import me.thens.ezgradle.config.configureKapt
import me.thens.ezgradle.config.configureKotlin
import me.thens.ezgradle.config.configureMavenPublish
import me.thens.ezgradle.lib.EzGradleBomManager
import me.thens.ezgradle.log.InternalLogger
import me.thens.ezgradle.log.log
import me.thens.ezgradle.model.EzGradleProperties
import me.thens.ezgradle.util.isAndroid
import me.thens.ezgradle.util.isJava
import me.thens.ezgradle.util.isRoot
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class EzGradleProjectPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        if (!target.isRoot) {
            return
        }
        target.gradle.afterProject {
            val ezgradleProperties = EzGradleProperties.from(project)
            InternalLogger.update {
                copy(printer = printer.filter(LogFilter.minLevel(ezgradleProperties.logLevel)))
            }
            log.debug { ezgradleProperties.toString() }
            configure(ezgradleProperties)
        }
    }

    private fun Project.configure(properties: EzGradleProperties) {
        configureAndroidApplication(properties)
        configureAndroidLibrary(properties)
        configureHilt()
        configureJava()
        configureJavaPlatform()
        configureKotlin()
        configureKapt()
        configureMavenPublish()
        if (properties.enableBom) {
            EzGradleBomManager(this).addDependencies(properties.libs.versions.ezgradle)
        }
        if (properties.generateBuildConfig && isJava && !isAndroid) {
            BuildConfigGenerator.registerTask(this)
        }
    }

    private val Project.isReleaseTask: Boolean
        get() = gradle.startParameter.taskNames.any { it.contains(Regex("[Rr]elease")) }
}