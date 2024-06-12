package me.thens.ezgradle.task

import java.nio.file.Path


data class TaskProps(
    val tomlPath: Path = Path.of("../gradle/libs.versions.toml"),
    val bomPath: Path = Path.of("../ezgradle-bom/build.gradle.kts"),
    val bomLibsStart: String = "//#LIBS_START\n",
    val bomLibsEnd: String = "\n//#LIBS_END",
)