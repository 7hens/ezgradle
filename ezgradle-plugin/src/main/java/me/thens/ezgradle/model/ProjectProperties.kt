package me.thens.ezgradle.model

import me.thens.ezgradle.util.toEnum
import me.thens.ezgradle.util.toEnvName
import org.gradle.api.Project

class ProjectProperties(val project: Project, val prefix: String = "") {
    fun with(nextPrefix: String): ProjectProperties {
        return ProjectProperties(project, "$prefix$nextPrefix")
    }

    operator fun get(name: String): String? {
        val key = "$prefix$name"
        return System.getenv(key.toEnvName()) ?: project.findProject(key)?.toString()
    }

    fun <E : Enum<E>> getEnum(name: String, defaultValue: E): E {
        return get(name)?.toEnum(defaultValue.javaClass) ?: defaultValue
    }
}