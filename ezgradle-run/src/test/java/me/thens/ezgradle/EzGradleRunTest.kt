package me.thens.ezgradle

import org.junit.Test

class EzGradleRunTest {

    @Test
    fun run() {
        val argv = System.getenv("EZGRADLE_ARGS").split(" ")
        EzGradleRun().run(argv)
    }
}