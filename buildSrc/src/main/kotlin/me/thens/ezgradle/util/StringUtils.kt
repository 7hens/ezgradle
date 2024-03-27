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
