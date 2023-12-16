import me.thens.ezgradle.lib.BuiltInLibs
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.apply
import java.io.File

fun DependencyHandler.builtInLibs(block: BuiltInLibs.() -> Unit) {
    block(BuiltInLibs(this))
}


