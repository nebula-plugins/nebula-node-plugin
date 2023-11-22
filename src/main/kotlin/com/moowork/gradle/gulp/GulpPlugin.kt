package com.moowork.gradle.gulp

import com.github.gradle.node.NodeExtension
import com.moowork.gradle.node.NodePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class GulpPlugin : Plugin<Project> {
    companion object {
        const val GULP_INSTALL_NAME = "installGulp"
    }
    override fun apply(project: Project) {

        project.plugins.apply(NodePlugin::class.java)
        val gulpExtension = GulpExtension.create(project)
        val nodeExtension = project.extensions.getByType(NodeExtension::class.java)
        project.tasks.register(GULP_INSTALL_NAME, GulpInstallTask::class.java)

        project.tasks.addRule("Pattern: \"gulp_<task>\": Executes a named gulp task.") { taskName ->
            if (taskName.startsWith("gulp_")) {
                project.tasks.register(taskName, GulpTask::class.java) {
                    it.args.set(listOf((taskName.removePrefix("gulp_"))))
                    it.script.convention(nodeExtension.nodeProjectDir.file("node_modules/gulp/bin/gulp.js"))
                    it.useBufferOutput.convention(gulpExtension.bufferOutput)
                }
            }
        }
    }
}