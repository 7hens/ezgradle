package me.thens.ezgradle.util

fun kotlin(name: String) = "org.jetbrains.kotlin.$name"

fun String.toVersionCode(): Int {
    return substringBefore("-")
        .replace(Regex("[^0-9.]"), "")
        .split(".")
        .map { it.toIntOrNull() ?: 0 }
        .plus(listOf(1, 0, 0))
        .take(3)
        .fold(0) { acc, i -> acc * 1000 + i }
}

fun String.toPackageName(): String {
    return lowercase()
        .replace(Regex("[^0-9a-z_.]"), "_")
        .replace(Regex("\\b[0-9_]"), "a\$0")
}

fun String.toEnvName(): String {
    return replace(Regex("\\W"), "_")
        .replace(Regex("([a-z])([A-Z])"), "\$1_\$2")
        .uppercase()
}

fun <E : Enum<E>> String.toEnum(cls: Class<E>): E? {
    val envName = toEnvName()
    return cls.enumConstants.firstOrNull { it.name.toEnvName() == envName }
}

inline fun <reified E : Enum<E>> String.toEnum(): E? {
    return toEnum(E::class.java)
}

fun <E : Enum<E>> String.toEnum(defaultValue: E): E {
    return toEnum(defaultValue.javaClass) ?: defaultValue
}

fun String.capitalize(): String {
    return replaceFirstChar { it.uppercase() }
}

fun String.decaptitalize(): String {
    return replaceFirstChar { it.lowercase() }
}

fun String.pascalCase(): String {
    return split(Regex("[^A-Za-z0-9]"))
        .joinToString("") { it.capitalize() }
}

fun String.camelCase(): String {
    return pascalCase().decaptitalize()
}

fun String.toCatalogAlias(): String {
    val (group, name) = split(":")
    return "${group.camelCase()}_${name.camelCase()}"
}

fun String.replaceRegion(content: String, start: String, end: String): String {
    val pattern = "($start)[\\w\\W]*?($end)"
    return Regex(pattern).replace(this, "\$1$content\$2")
}
