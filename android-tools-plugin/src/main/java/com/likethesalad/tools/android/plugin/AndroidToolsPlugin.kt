package com.likethesalad.tools.android.plugin

import com.likethesalad.tools.android.plugin.data.strategies.PluginStrategy
import com.likethesalad.tools.android.plugin.extension.AndroidToolsPluginExtension
import com.likethesalad.tools.android.plugin.extension.observable.VariantPublisher
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidToolsPlugin : Plugin<Project> {

    private lateinit var publisher: VariantPublisher

    companion object {
        private const val EXTENSION_NAME = "androidTools"
    }

    override fun apply(project: Project) {
        publisher = VariantPublisher()
        val extension = createExtension(project)
        val pluginStrategy = PluginStrategy.getStrategy(project)
        extension.androidExtension = pluginStrategy.configure(project, publisher)
    }

    private fun createExtension(project: Project): AndroidToolsPluginExtension {
        return project.extensions.create(EXTENSION_NAME, AndroidToolsPluginExtension::class.java, publisher)
    }
}