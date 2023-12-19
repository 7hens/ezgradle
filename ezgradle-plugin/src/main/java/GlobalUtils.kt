import me.thens.ezgradle.lib.BuiltInLibs
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.builtInLibs(block: BuiltInLibs.() -> Unit) {
    block(BuiltInLibs(this))
}


