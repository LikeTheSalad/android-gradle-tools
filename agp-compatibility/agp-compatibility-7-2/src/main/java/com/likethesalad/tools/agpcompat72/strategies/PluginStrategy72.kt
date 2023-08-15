package com.likethesalad.tools.agpcompat72.strategies

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import com.likethesalad.tools.agpcompat.api.PluginStrategy
import com.likethesalad.tools.agpcompat.api.bridges.AndroidExtension
import com.likethesalad.tools.agpcompat.api.observable.VariantPublisher
import com.likethesalad.tools.agpcompat72.bridges.AndroidExtension72
import com.likethesalad.tools.agpcompat72.bridges.AndroidVariantData72
import org.gradle.api.Project

class PluginStrategy72 : PluginStrategy {

    override fun configure(project: Project, publisher: VariantPublisher): AndroidExtension {
        val androidExtension = project.extensions.getByType(BaseExtension::class.java)
        when (androidExtension) {
            is AppExtension -> configureAppExtension(project, publisher, androidExtension)
            is LibraryExtension -> configureLibraryExtension(project, publisher, androidExtension)
            else -> throw UnsupportedOperationException("Android extension type not supported")
        }
        return AndroidExtension72(androidExtension)
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
        val variant = AndroidVariantData72(project, androidVariant)
        publisher.publish(variant)
    }
}