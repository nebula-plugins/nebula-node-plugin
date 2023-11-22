package com.moowork.gradle.node

import com.github.gradle.node.NodeExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

 class NodePlugin : Plugin<Project> {
     companion object {
         /**
          * Default version of Node to download if none is set
          */
         const val DEFAULT_NODE_VERSION = "18.17.1"

         /**
          * Default version of npm to download if none is set
          */
         const val DEFAULT_NPM_VERSION = "9.6.7"
     }
     override fun apply(project: Project) {
         project.pluginManager.apply(com.github.gradle.node.NodePlugin::class.java)
         val nodeExtension = project.extensions.findByType(NodeExtension::class.java) ?: return
         nodeExtension.version.convention(DEFAULT_NODE_VERSION)
         nodeExtension.npmVersion.convention(DEFAULT_NPM_VERSION)
     }
}