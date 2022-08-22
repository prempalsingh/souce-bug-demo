import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceTask
import java.io.File

class CustomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            project.extensions.findByType(com.android.build.gradle.AppExtension::class.java)?.applicationVariants?.all { variant ->
                val output = File("${project.buildDir}/generated/source/test/${variant.dirName}")
                val capitalizedName = variant.name.capitalize()

                val config: GenerateClassTask.() -> Unit = {
                    outputDir = output
                }

                val taskProvider = project.tasks.register("generateClassFor${capitalizedName}", GenerateClassTask::class.java).also {
                    it.configure(config)
                }


                (project.tasks.findByName("compile${capitalizedName}Kotlin") as? SourceTask)?.also {
                    it.dependsOn(taskProvider)
                    it.source(output)
                }

                variant.registerJavaGeneratingTask(taskProvider, output)
                true
            }
        }
    }
}