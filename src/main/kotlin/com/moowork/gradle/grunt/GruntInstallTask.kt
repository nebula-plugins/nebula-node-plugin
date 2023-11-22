package com.moowork.gradle.grunt


import com.github.gradle.node.npm.task.NpmTask

abstract class GruntInstallTask : NpmTask()  {
    init {
        group = "Grunt"
        description = "Runs 'npm install grunt-cli grunt' to install grunt"
        npmCommand.set(listOf("install", "grunt-cli", "grunt"))
    }
}
