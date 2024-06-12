package me.thens.ezgradle

import me.thens.ezgradle.task.UpdateBomTask
import me.thens.ezgradle.task.UpdateLibNameTask

class EzGradleRun : EzGradleTask {
    private val tasks = listOf(
        UpdateBomTask(),
        UpdateLibNameTask(),
    ).associateBy { it.taskName }

    override fun run(argv: List<String>) {
        val taskName = argv[0]
        val taskArgs = argv.drop(1)
        tasks.getValue(taskName).run(taskArgs)
    }
}