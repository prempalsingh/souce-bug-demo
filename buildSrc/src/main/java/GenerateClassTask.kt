import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

open class GenerateClassTask : DefaultTask() {

    @OutputDirectory
    var outputDir: File? = null

    @TaskAction
    fun generateConfig() {
        val outputFile = File(outputDir, "/GeneratedClass.kt")


        outputFile.writeText(
            //language=kotlin
            """
            package abc.def.ghi

            object GeneratedClass {
                @JvmField val versionName: String = "10.2.0"
            }
            """.trimIndent()
        )
    }
}