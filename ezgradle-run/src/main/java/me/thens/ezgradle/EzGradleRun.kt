package me.thens.ezgradle

import me.thens.ezgradle.task.GenerateBuildConfigTask

class EzGradleRun : EzGradleTask {
    private val tasks = mapOf(
        "generateBuildConfig" to GenerateBuildConfigTask(),
    )

    override fun run(argv: List<String>) {
        val taskName = argv[0]
        val taskArgs = argv.drop(1)
        tasks.getValue(taskName).run(taskArgs)
    }
}