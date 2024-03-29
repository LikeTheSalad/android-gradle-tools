package com.likethesalad.tools.android.plugin

import com.likethesalad.tools.agpcompat.api.AgpCompatibilityEntrypoint
import com.likethesalad.tools.agpcompat.api.observable.VariantPublisher
import com.likethesalad.tools.android.plugin.extension.AndroidToolsPluginExtension
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
        val pluginStrategy = AgpCompatibilityEntrypoint.findCompatibleStrategy(project)
        extension.androidExtension = pluginStrategy.configure(project, publisher)
    }

    private fun createExtension(project: Project): AndroidToolsPluginExtension {
        return project.extensions.create(EXTENSION_NAME, AndroidToolsPluginExtension::class.java, publisher)
    }
}