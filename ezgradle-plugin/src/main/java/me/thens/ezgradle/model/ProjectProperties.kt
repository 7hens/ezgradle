package me.thens.ezgradle.model

import me.thens.ezgradle.util.loadProperties
import me.thens.ezgradle.util.toEnum
import me.thens.ezgradle.util.toEnvName
import org.gradle.api.Project

data class ProjectProperties(
    val project: Project,
    val properties: Map<String, String>,
    val prefix: String,
) {
    fun with(nextPrefix: String): ProjectProperties {
        return copy(prefix = "$prefix$nextPrefix")
    }

    operator fun get(name: String): String? {
        val key = "$prefix$name"
        val envName = key.toEnvName()
        return System.getenv(envName) ?: listOf(key, envName)
            .flatMap {
                listOfNotNull(
                    System.getProperty(it),
                    properties[it],
                    project.findProperty(it)?.toString(),
                )
            }
            .firstOrNull()
    }

    fun getValue(name: String): String {
        return get(name)
            ?: throw NullPointerException("Cannot find value for key '$name in ProjectProperties")
    }

    fun <E : Enum<E>> getEnum(name: String, defaultValue: E): E {
        return get(name)?.toEnum(defaultValue.javaClass) ?: defaultValue
    }

    companion object {
        fun from(project: Project): ProjectProperties {
            val properties = project.loadProperties("local.properties")
            return ProjectProperties(project, properties, "")
        }
    }
}