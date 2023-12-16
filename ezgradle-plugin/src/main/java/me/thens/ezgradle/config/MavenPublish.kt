package me.thens.ezgradle.config

import me.thens.ezgradle.misc.configure
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create

internal fun Project.configureMavenPublish() {
    if (plugins.hasPlugin("maven-publish")) {
        afterEvaluate {
            configure<PublishingExtension>("publishing") {
                repositories { mavenLocal() }
                publications {
                    create<MavenPublication>("my") {
                        from(components.getByName("release"))
                    }
                }
            }
            tasks.findByName("publishMyPublicationToMavenLocal")!!.dependsOn("assembleRelease")
        }
    }
}