package com.likethesalad.tools.android.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import com.likethesalad.tools.android.plugin.data.impl.DefaultAndroidExtension
import com.likethesalad.tools.android.plugin.data.impl.DefaultAndroidVariantData
import com.likethesalad.tools.android.plugin.extension.AndroidToolsPluginExtension
import com.likethesalad.tools.android.plugin.extension.observable.VariantPublisher
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidToolsPlugin : Plugin<Project> {

    private lateinit var publisher: VariantPublisher
    private lateinit var extension: AndroidToolsPluginExtension

    companion object {
        private const val EXTENSION_NAME = "androidTools"
    }

    override fun apply(project: Project) {
        val androidExtension = project.extensions.getByType(BaseExtension::class.java)
        publisher = VariantPublisher()
        createExtension(project, androidExtension)

        when (androidExtension) {
            is AppExtension -> configureAppExtension(androidExtension)
            is LibraryExtension -> configureLibraryExtension(androidExtension)
            else -> throw UnsupportedOperationException("Android extension type not supported")
        }
    }

    private fun configureLibraryExtension(androidExtension: LibraryExtension) {
        androidExtension.libraryVariants.all {
            addVariantData(it)
        }
    }

    private fun configureAppExtension(androidExtension: AppExtension) {
        androidExtension.applicationVariants.all {
            addVariantData(it)
        }
    }

    private fun addVariantData(androidVariant: BaseVariant) {
        val variant = DefaultAndroidVariantData(androidVariant)
        publisher.publish(variant)
    }

    private fun createExtension(project: Project, androidExtension: BaseExtension) {
        extension = project.extensions.create(EXTENSION_NAME, AndroidToolsPluginExtension::class.java, publisher)
        extension.androidExtension = DefaultAndroidExtension(androidExtension)
    }
}