package me.thens.ezgradle

import me.thens.ezgradle.config.configureAndroidApplication
import me.thens.ezgradle.config.configureAndroidLibrary
import me.thens.ezgradle.config.configureHilt
import me.thens.ezgradle.config.configureJava
import me.thens.ezgradle.config.configureJavaPlatform
import me.thens.ezgradle.config.configureKapt
import me.thens.ezgradle.config.configureKotlin
import me.thens.ezgradle.config.configureMavenPublish
import me.thens.ezgradle.misc.generateBuildConfig
import me.thens.ezgradle.misc.isJava
import me.thens.ezgradle.misc.toPackageName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
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
        configureAndroidApplication()
        configureAndroidLibrary()
        configureHilt()
        configureJava()
        configureJavaPlatform()
        configureKotlin()
        configureKapt()
        configureMavenPublish()
        val ezGradleGroup = BuildConfig.GROUP
        val ezGradleVersion = BuildConfig.VERSION
        dependencies {
            platform("$ezGradleGroup:ezgradle-bom:$ezGradleVersion").let { platform ->
                listOf("implementation", "androidTestImplementation", "kapt", "annotationProcessor")
                    .filter { configurations.findByName(it) != null }
                    .forEach { add(it, platform) }
            }
        }
        afterEvaluate {
            tasks.filter { it.name.startsWith("compile") && it.name.endsWith("Kotlin") }
                .forEach {
                    it.doFirst {
                        if (isJava) {
                            val packageName = "${project.group}.${projectDir.name}"
                            generateBuildConfig(packageName.toPackageName())
                        }
                    }
                }
        }
    }
}