package com.likethesalad.tools.android.plugin.data.strategies.impl

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import com.likethesalad.tools.android.plugin.data.AndroidExtension
import com.likethesalad.tools.android.plugin.data.impl.DefaultAndroidExtension
import com.likethesalad.tools.android.plugin.data.impl.DefaultAndroidVariantData
import com.likethesalad.tools.android.plugin.data.strategies.PluginStrategy
import com.likethesalad.tools.android.plugin.extension.observable.VariantPublisher
import org.gradle.api.Project

class LegacyPluginStrategy : PluginStrategy {

    override fun configure(project: Project, publisher: VariantPublisher): AndroidExtension {
        val androidExtension = project.extensions.getByType(BaseExtension::class.java)
        when (androidExtension) {
            is AppExtension -> configureAppExtension(project, publisher, androidExtension)
            is LibraryExtension -> configureLibraryExtension(project, publisher, androidExtension)
            else -> throw UnsupportedOperationException("Android extension type not supported")
        }
        return DefaultAndroidExtension(androidExtension)
    }

    private fun configureLibraryExtension(
        project: Project,
        publisher: VariantPublisher,
        androidExtension: LibraryExtension
    ) {
        androidExtension.libraryVariants.all {
            addVariantData(project, publisher, it)
        }
    }

    private fun configureAppExtension(project: Project, publisher: VariantPublisher, androidExtension: AppExtension) {
        androidExtension.applicationVariants.all {
            addVariantData(project, publisher, it)
        }
    }

    private fun addVariantData(project: Project, publisher: VariantPublisher, androidVariant: BaseVariant) {
        val variant = DefaultAndroidVariantData(project, androidVariant)
        publisher.publish(variant)
    }
}