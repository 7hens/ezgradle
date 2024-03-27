import me.thens.ezgradle.dependency.DependencyDoctor
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.TaskContainer
import org.gradle.plugin.use.PluginDependency

fun TaskContainer.registerLogDependencies() {
    create("logDependencies") {
        doLast {
            DependencyDoctor(project).run()
        }
    }
}

fun Provider<PluginDependency>.plugin(): Provider<String> {
    return map { it.run { "$pluginId:$pluginId.gradle.plugin:$version" } }
}
