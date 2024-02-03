package me.thens.ezgradle.config

import me.thens.ezgradle.util.configure
import me.thens.ezgradle.util.isAndroid
import me.thens.ezgradle.util.isJava
import me.thens.ezgradle.util.isJavaPlatform
import me.thens.ezgradle.util.isRoot
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create

internal fun Project.configureMavenPublish() {
    if (plugins.hasPlugin("maven-publish")) {
        when {
            isAndroid -> configureMavenPublish("assembleRelease", "release")
            isJavaPlatform -> configureMavenPublish("assemble", "javaPlatform")
            isJava -> configureMavenPublish("assemble", "java")
            else -> Unit
        }
    }
//    if (isRoot) {
//        configureMavenPublishInRoot()
//    }
}

private fun Project.configureMavenPublish(assembleTask: String, component: String) {
    afterEvaluate {
        configure<PublishingExtension>("publishing") {
            repositories { mavenLocal() }
            publications {
                create<MavenPublication>("my") {
                    groupId = project.group.toString()
                    artifactId = project.name
                    version = project.version.toString()
                    from(components.findByName(component))
                }
            }
        }
        tasks.getByName("publishMyPublicationToMavenLocal").apply {
            dependsOn(assembleTask)
        }
    }
}

private fun Project.configureMavenPublishInRoot() {
    tasks.maybeCreate("publishToMavenLocal").apply {
        gradle.includedBuilds.forEach {
            try {
                dependsOn(it.task(":publishToMavenLocal"))
            } catch (error: Throwable) {
                println(error.stackTraceToString())
            }
        }
    }
}