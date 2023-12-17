package me.thens.ezgradle.misc

fun kotlin(name: String) = "org.jetbrains.kotlin.$name"

fun String.toVersionCode(): Int {
    return substringBefore("-")
        .replace(Regex("[^0-9.]"), "")
        .split(".")
        .map { it.toIntOrNull() ?: 0 }
        .plus(listOf(0, 0, 0))
        .take(3)
        .fold(0) { acc, i -> acc * 1000 + i }
}

fun String.toPackageName(): String {
    return lowercase()
        .replace(Regex("[^0-9a-z_.]"), "_")
        .replace(Regex("\\b\\d"), "_\$0")
}
