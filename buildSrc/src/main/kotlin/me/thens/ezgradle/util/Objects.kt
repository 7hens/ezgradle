package me.thens.ezgradle.util

@Suppress("UNCHECKED_CAST")
fun Any.asMap(): Map<String, Any?>? = this as? Map<String, Any?>

fun Any.asList(): List<Any?>? = this as? List<Any?>
