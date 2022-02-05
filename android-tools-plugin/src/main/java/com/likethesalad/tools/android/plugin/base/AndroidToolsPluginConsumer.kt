package com.likethesalad.tools.android.plugin.base

import com.likethesalad.tools.android.plugin.AndroidToolsPlugin
import com.likethesalad.tools.android.plugin.extension.AndroidToolsPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidToolsPluginConsumer : Plugin<Project> {

    protected lateinit var androidTools: AndroidToolsPluginExtension

    override fun apply(project: Project) {
        androidTools = findAndroidToolsPluginExtension(project)
    }

    private fun findAndroidToolsPluginExtension(project: Project): AndroidToolsPluginExtension {
        val toolsPluginExtension = project.extensions.findByType(AndroidToolsPluginExtension::class.java)
        if (toolsPluginExtension == null) {
            project.plugins.apply(AndroidToolsPlugin::class.java)
            return project.extensions.getByType(AndroidToolsPluginExtension::class.java)
        }

        return toolsPluginExtension
    }
}