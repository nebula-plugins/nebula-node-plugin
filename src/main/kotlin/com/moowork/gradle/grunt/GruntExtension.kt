package com.moowork.gradle.grunt

import org.gradle.api.Project

open class GruntExtension(project: Project) {
    private val cacheDir = project.layout.projectDirectory.dir(".gradle")

    val workDir = project.objects.directoryProperty().convention(cacheDir.dir("nodejs"))

    val bufferOutput = project.objects.property(Boolean::class.java).convention(true)

    val gruntFile = project.objects.property(String::class.java).convention("Gruntfile.js")

    companion object {
        const val NAME = "grunt"
        @JvmStatic
        fun create(project: Project): GruntExtension {
            return project.extensions.create(GruntExtension.NAME, GruntExtension::class.java, project)
        }
    }
}