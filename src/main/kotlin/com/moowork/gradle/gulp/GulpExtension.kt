package com.moowork.gradle.gulp

import org.gradle.api.Project

open class GulpExtension(project: Project) {
    private val cacheDir = project.layout.projectDirectory.dir(".gradle")

    val workDir = project.objects.directoryProperty().convention(cacheDir.dir("nodejs"))

    val bufferOutput = project.objects.property(Boolean::class.java).convention(false)

    companion object {
        const val NAME = "gulp"
        @JvmStatic
        fun create(project: Project): GulpExtension {
            return project.extensions.create(GulpExtension.NAME, GulpExtension::class.java, project)
        }
    }
}