package me.thens.ezgradle.util

import org.gradle.api.Project

fun Project.shell(command: String): String {
    println("$> $command")
    val process = ProcessBuilder()
        .command("bash", "-c", command)
        .redirectErrorStream(true)
        .apply { environment().putAll(System.getenv()) }
        .start()
    val separator = System.getProperty("line.separator")
    return process.inputReader().lineSequence()
        .onEach { println(it) }
        .joinToString(separator)
        .trim()
}
