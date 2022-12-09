package com.likethesalad.tools.android.plugin.data.strategies

import com.likethesalad.tools.android.plugin.data.AndroidExtension
import com.likethesalad.tools.android.plugin.extension.observable.VariantPublisher
import com.likethesalad.tools.android.plugin.version.AgpApiHelper
import org.gradle.api.Project

interface PluginStrategy {

    companion object {
        fun getStrategy(project: Project): PluginStrategy {
            return if (AgpApiHelper.isLegacyApi(project)) {
                project.logger.info("Using legacy plugin strategy")
                Class.forName("com.likethesalad.tools.android.plugin.data.strategies.impl.LegacyPluginStrategy")
                    .getDeclaredConstructor().newInstance() as PluginStrategy
            } else {
                project.logger.info("Using default plugin strategy")
                Class.forName("com.likethesalad.tools.android.plugin.data.strategies.impl.DefaultPluginStrategy")
                    .getDeclaredConstructor().newInstance() as PluginStrategy
            }
        }
    }

    fun configure(project: Project, publisher: VariantPublisher): AndroidExtension
}