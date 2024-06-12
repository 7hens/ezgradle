package me.thens.ezgradle.util

import java.io.File
import java.util.Properties

fun Properties.load(file: File): Properties {
    file.takeIf { it.isFile }?.inputStream()?.use { load(it) }
    return this
}

fun Properties.toStringMap(): Map<String, String> {
    return entries.associate { it.key.toString() to it.value.toString() }
}
