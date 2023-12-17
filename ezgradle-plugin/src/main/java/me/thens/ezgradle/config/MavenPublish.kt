package me.thens.ezgradle.config

import me.thens.ezgradle.misc.configure
import me.thens.ezgradle.misc.isAndroid
import me.thens.ezgradle.misc.isJava
import me.thens.ezgradle.misc.isRoot
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create

internal fun Project.configureMavenPublish() {
    if (plugins.hasPlugin("maven-publish")) {
        if (isAndroid) {
            configureMavenPublish("release", "assembleRelease")
        } else if (isJava) {
            configureMavenPublish("java", "assemble")
        }
    }
    if (isRoot) {
        configureMavenPublishInRoot()
    }
}

private fun Project.configureMavenPublish(component: String, assembleTask: String) {
    afterEvaluate {
        configure<PublishingExtension>("publishing") {
            repositories { mavenLocal() }
            publications {
                create<MavenPublication>("my") {
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