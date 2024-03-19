package me.thens.ezgradle

import me.thens.ezgradle.config.configureAndroidApplication
import me.thens.ezgradle.config.configureAndroidLibrary
import me.thens.ezgradle.config.configureHilt
import me.thens.ezgradle.config.configureJava
import me.thens.ezgradle.config.configureJavaPlatform
import me.thens.ezgradle.config.configureKapt
import me.thens.ezgradle.config.configureKotlin
import me.thens.ezgradle.config.configureMavenPublish
import me.thens.ezgradle.lib.EzGradleBomManager
import me.thens.ezgradle.util.GenerateBuildConfigTask
import me.thens.ezgradle.util.isAndroid
import me.thens.ezgradle.util.isJava
import me.thens.ezgradle.model.EzGradleProperties
import org.gradle.api.Plugin
import org.gradle.api.Project

class EzGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val ezgradleProperties = EzGradleProperties.from(target)
        target.configure(ezgradleProperties)
    }

    private fun Project.configure(ezGradleProperties: EzGradleProperties) {
        configureAndroidApplication()
        configureAndroidLibrary()
        configureHilt()
        configureJava()
        configureJavaPlatform()
        configureKotlin()
        configureKapt()
        configureMavenPublish()
        EzGradleBomManager(this).addDependencies(ezGradleProperties.bomVersion)
        if (ezGradleProperties.generateBuildConfig && isJava && !isAndroid) {
            GenerateBuildConfigTask.register(this)
        }
    }

    private val Project.isReleaseTask: Boolean
        get() = gradle.startParameter.taskNames.any { it.contains(Regex("[Rr]elease")) }
}