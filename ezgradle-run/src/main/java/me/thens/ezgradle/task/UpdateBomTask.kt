package me.thens.ezgradle.task

import me.thens.ezgradle.NamedEzGradleTask
import me.thens.ezgradle.util.replaceRegion
import org.tomlj.Toml
import kotlin.io.path.readText
import kotlin.io.path.writeText

class UpdateBomTask : NamedEzGradleTask {
    override fun run(argv: List<String>) {
        val props = TaskProps()
        val tomlData = Toml.parse(props.tomlPath)
        val libs = tomlData.getTable("libraries")?.keySet() ?: emptySet()
        val plugins = tomlData.getTable("plugins")?.keySet() ?: emptySet()
        val (bomLibs, nonBomLibs) = libs.partition { it.endsWith("bom", ignoreCase = true) }
        val content = sequence {
            val ind = "    "
            yieldAll(bomLibs.map { "${ind}api(platform(libs.${libAccessor(it)}))" })
            yield("${ind}constraints {")
            yield(nonBomLibs.map { "${ind}${ind}api(libs.${libAccessor(it)})" })
            yieldAll(plugins.map { "${ind}${ind}api(libs.plugins.${libAccessor(it)}.plugin())" })
            yield("${ind}}")
        }.joinToString("\n")
        val oldText = props.bomPath.readText()
        val newText = oldText.replaceRegion(content, props.bomLibsStart, props.bomLibsEnd)
        props.bomPath.writeText(newText)
    }

    private fun libAccessor(libName: String): String {
        return libName.replace("-", ".")
    }
}