package me.thens.ezgradle.model

import me.thens.ezgradle.util.toEnum
import me.thens.ezgradle.util.toEnvName
import org.gradle.api.Project

class ProjectProperties(val project: Project, val prefix: String = "") {
    fun with(nextPrefix: String): ProjectProperties {
        return ProjectProperties(project, "$prefix$nextPrefix")
    }

    fun getString(name: String): String? {
        val key = "$prefix$name"
        return project.findProject(key)?.toString()
            ?: System.getenv(key.toEnvName())
    }

    fun getBoolean(name: String): Boolean? {
        return getString(name)?.toBoolean()
    }

    fun getInt(name: String): Int? {
        return getString(name)?.toIntOrNull()
    }

    fun getLong(name: String): Long? {
        return getString(name)?.toLongOrNull()
    }

    fun getFloat(name: String): Float? {
        return getString(name)?.toFloatOrNull()
    }

    fun getDouble(name: String): Double? {
        return getString(name)?.toDoubleOrNull()
    }

    fun <E : Enum<E>> getEnum(name: String, defaultValue: E): E {
        return getString(name)?.toEnum(defaultValue.javaClass) ?: defaultValue
    }
}