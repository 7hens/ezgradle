package me.thens.ezgradle.model

import me.thens.ezgradle.util.loadProperties
import org.gradle.api.Project
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BuildConfigGenerator(private val project: Project) {

    private fun String.matchesPropKey(): Boolean {
        return matches(Regex("[A-Z_]+"))
    }

    fun getProperties(): Map<String, String> {
        val gradleProperties = project.loadProperties("gradle.properties")
        val localProperties = project.loadProperties("local.properties")
        val keys = (gradleProperties.keys + localProperties.keys).filter { it.matchesPropKey() }
        val projectProperties = ProjectProperties(project, localProperties, "")
        return keys.associateWith { projectProperties.getValue(it) } + getExtraProperties()
    }

    private fun getExtraProperties(): Map<String, String> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        return mapOf(
            "GROUP" to project.group.toString(),
            "NAME" to project.name,
            "VERSION" to project.version.toString(),
            "BUILD_TIME" to dateFormat.format(Date()),
        )
    }
}