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
import me.thens.ezgradle.misc.GenerateBuildConfigTask
import me.thens.ezgradle.misc.isAndroid
import me.thens.ezgradle.misc.isJava
import org.gradle.api.Plugin
import org.gradle.api.Project

class EzGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.configure()
    }

    private fun Project.configure() {
        configureAndroidApplication()
        configureAndroidLibrary()
        configureHilt()
        configureJava()
        configureJavaPlatform()
        configureKotlin()
        configureKapt()
        configureMavenPublish()
        EzGradleBomManager(this).addDependencies()
        if (isJava && !isAndroid) {
            GenerateBuildConfigTask.register(this)
        }
    }
}