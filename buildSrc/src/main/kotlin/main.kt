import me.thens.ezgradle.dependency.DependencyDoctor
import org.gradle.api.tasks.TaskContainer

fun TaskContainer.registerLogDependencies() {
    create("logDependencies") {
        doLast {
            DependencyDoctor(project).run()
        }
    }
}
