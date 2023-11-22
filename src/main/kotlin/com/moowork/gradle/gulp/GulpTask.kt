package com.moowork.gradle.gulp

import com.github.gradle.node.exec.NodeExecConfiguration
import com.github.gradle.node.exec.NodeExecRunner
import com.github.gradle.node.task.NodeTask
import com.github.gradle.node.variant.VariantComputer
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault
import java.io.ByteArrayOutputStream

@DisableCachingByDefault
abstract class GulpTask : NodeTask() {

    @get:Input
    val useBufferOutput = objects.property(Boolean::class.java).convention(true)

    @TaskAction
    fun execute() {
        if (!script.isPresent || !script.get().asFile.isFile) {
            throw GradleException( "gulp not installed in node_modules, please first run 'gradle ${GulpPlugin.GULP_INSTALL_NAME}'" )
        }

        // If output should be buffered (useful when running in parallel)
        // set standardOutput of ExecRunner to a buffer
        var bufferedOutput : ByteArrayOutputStream? = null
        if( useBufferOutput.isPresent && useBufferOutput.get()){
            bufferedOutput = ByteArrayOutputStream()
            execOverrides.set {
                it.standardOutput = bufferedOutput
            }
        }


        // If the exec fails, make sure to still print output
        try {
            val currentScript = script.get().asFile
            val command = options.get().plus(currentScript.absolutePath).plus(args.get())
            val nodeExecConfiguration =
                NodeExecConfiguration(command, environment.get(), workingDir.asFile.orNull,
                    ignoreExitValue.get(), execOverrides.orNull)
            val nodeExecRunner = NodeExecRunner()
            result = nodeExecRunner.execute(projectHelper, extension, nodeExecConfiguration, VariantComputer())
        }
        finally {
            // If we were buffering output, print it
            if ( useBufferOutput.isPresent && useBufferOutput.get() && ( bufferedOutput != null ) ) {
                println("Output from gulpfile.js")
                println(bufferedOutput.toString())
            }
        }
    }
}