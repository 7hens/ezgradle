package me.thens.ezgradle

fun interface EzGradleTask {
    fun run(argv: List<String>)
}