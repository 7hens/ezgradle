package me.thens.ezgradle.task

import me.thens.ezgradle.NamedEzGradleTask
import org.tomlj.Toml
import org.tomlj.TomlCommand
import org.tomlj.TomlTable

class UpdateLibNameTask: NamedEzGradleTask {
    override fun run(argv: List<String>) {
        val props = TaskProps()
        val tomlData = Toml.parse(props.tomlPath)
        val libs = tomlData.getTable("libraries")
        val plugins = tomlData.getTable("plugins")
    }
}