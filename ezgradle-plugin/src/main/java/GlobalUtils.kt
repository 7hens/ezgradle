import me.thens.ezgradle.lib.BuiltInLibs
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

fun Project.builtInLibs(block: BuiltInLibs.() -> Unit) {
    block(BuiltInLibs(this, dependencies))
}


