package me.thens.ezgradle

import me.thens.ezgradle.util.decaptitalize

interface NamedEzGradleTask : EzGradleTask {
    val taskName: String get() = getDefaultTaskName()

    private fun getDefaultTaskName(): String {
        return this::class.java.simpleName.substringBeforeLast("Task").decaptitalize()
    }
}