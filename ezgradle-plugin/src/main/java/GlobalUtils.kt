import me.thens.ezgradle.lib.BuiltInLibs
import me.thens.ezgradle.model.EzGradleProperties
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

fun Project.builtInLibs(block: BuiltInLibs.() -> Unit) {
    block(BuiltInLibs(this, EzGradleProperties.from(this), dependencies))
}

fun Project.localProject(name: String): Any {
    return findProject(name) ?: run {
        val projectName = name.substringAfterLast(":")
        "$group:$projectName:$version"
    }
}

fun Provider<PluginDependency>.plugin(): Provider<String> {
    return map { it.run { "$pluginId:$pluginId.gradle.plugin:$version" } }
}

