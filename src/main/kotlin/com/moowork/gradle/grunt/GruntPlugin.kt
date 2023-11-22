package com.moowork.gradle.grunt

import com.github.gradle.node.NodeExtension
import com.moowork.gradle.node.NodePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class GruntPlugin : Plugin<Project> {
    companion object {
        const val GRUNT_INSTALL_NAME = "installGrunt"
    }
    override fun apply(project: Project) {

        project.plugins.apply(NodePlugin::class.java)
        val gulpExtension = GruntExtension.create(project)
        val nodeExtension = project.extensions.getByType(NodeExtension::class.java)
        val gruntInstall =project.tasks.register(GRUNT_INSTALL_NAME, GruntInstallTask::class.java)

        project.tasks.addRule("Pattern: \"grunt_<task>\": Executes a named gulp task.") { taskName ->
            if (taskName.startsWith("grunt_")) {
                project.tasks.register(taskName, GruntTask::class.java) {
                    it.args.set(listOf((taskName.removePrefix("grunt_"))))
                    it.script.convention(nodeExtension.nodeProjectDir.file("node_modules/grunt-cli/bin/grunt"))
                    it.useBufferOutput.convention(gulpExtension.bufferOutput)
                    it.gruntFile.convention(nodeExtension.nodeProjectDir.file(gulpExtension.gruntFile))
                }
            }
        }
    }
}