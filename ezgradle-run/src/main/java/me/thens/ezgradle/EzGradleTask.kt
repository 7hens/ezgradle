package me.thens.ezgradle

interface EzGradleTask {
    fun run(argv: List<String>)
}