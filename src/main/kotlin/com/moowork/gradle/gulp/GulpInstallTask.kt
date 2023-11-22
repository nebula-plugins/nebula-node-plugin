package com.moowork.gradle.gulp


import com.github.gradle.node.npm.task.NpmTask
import org.gradle.api.tasks.CacheableTask

@CacheableTask
abstract class GulpInstallTask : NpmTask()  {
    init {
        group = "Gulp"
        description = "Runs 'npm install gulp' to install gulp"
        npmCommand.set(listOf("install", "gulp"))
    }
}
