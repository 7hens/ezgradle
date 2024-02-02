import org.gradle.api.Project
import me.thens.ezgradle.DependencyDoctor

fun Project.createTask() {
    tasks.create("outdatedDependencies") {
        doLast {
            DependencyDoctor(project).run()
        }
    }
}
