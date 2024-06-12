package me.thens.ezgradle.task

import me.thens.ezgradle.NamedEzGradleTask
import me.thens.ezgradle.util.camelCase
import me.thens.ezgradle.util.toCatalogAlias
import org.tomlj.TomlTable
import kotlin.io.path.readText
import kotlin.io.path.writeText

class UpdateLibNameTask : NamedEzGradleTask {
    override fun run(argv: List<String>) {
        val props = TaskProps()
        val tomlText = props.tomlPath.readText()
        // Update module names and plugin names
        val moduleRegex = Regex("[\\w\\-]+ = (\\{ (module|id) = \"([^\"]+)\".*\\})")
        var tomlResult = moduleRegex.replace(tomlText) { result ->
            val library = result.groups[1]!!.value
            val module = result.groups[3]!!.value
            "${module.toCatalogAlias()} = $library"
        }
        // Replace version names
        tomlResult = Regex("\n([\\w\\-]+)( = \".*\")").replace(tomlResult) { result ->
            val name = result.groups[1]!!.value
            val value = result.groups[2]!!.value
            "\n${name.camelCase()}$value"
        }
        tomlResult = Regex("(version.ref = )\"(.*)\"").replace(tomlResult) { result ->
            val head = result.groups[1]!!.value
            val name = result.groups[2]!!.value
            "$head\"${name.camelCase()}\""
        }
        // Sort lines
        tomlResult = Regex("(\\[[\\w.]+]\n)([^\\[]+)").replace(tomlResult) { result ->
            val head = result.groups[1]!!.value
            val content = result.groups[2]!!.value
            "$head${content.lines().filter { it.isNotBlank() }.sorted().joinToString("\n")}\n\n"
        }
//        println(tomlResult)
        props.tomlPath.writeText(tomlResult)
    }

    private fun Any.toLibraryModule(): String {
        if (this is String) {
            return substringBeforeLast(":")
        }
        require(this is TomlTable)
        return getString("module") ?: "${getString("group")}:${getString("name")}"
    }
}