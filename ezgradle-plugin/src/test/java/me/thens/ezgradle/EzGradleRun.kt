package me.thens.ezgradle

import org.junit.Test

class EzGradleRun {
    @Test
    fun run() {
        val args = System.getenv("EZGRADLE_ARGS")
        println("Hello, world: $args")
    }
}