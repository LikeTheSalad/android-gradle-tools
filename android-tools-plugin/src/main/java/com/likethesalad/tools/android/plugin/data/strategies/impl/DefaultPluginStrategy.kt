package com.likethesalad.tools.android.plugin.data.strategies.impl

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.Variant
import com.likethesalad.tools.android.plugin.data.impl.componentextension.ComponentAndroidExtension
import com.likethesalad.tools.android.plugin.data.impl.componentextension.ComponentAndroidVariantData
import com.likethesalad.tools.android.plugin.data.strategies.PluginStrategy
import com.likethesalad.tools.android.plugin.extension.observable.VariantPublisher
import org.gradle.api.Project

class DefaultPluginStrategy : PluginStrategy {

    override fun configure(project: Project, publisher: VariantPublisher): AndroidExtension {
        val androidExtension = project.extensions.getByType(CommonExtension::class.java)
        val componentsExtension = project.extensions.getByType(AndroidComponentsExtension::class.java)

        componentsExtension.onVariants {
            addVariantData(it, publisher)
        }

        return ComponentAndroidExtension(androidExtension)
    }

    private fun addVariantData(variant: Variant, publisher: VariantPublisher) {
        val androidVariantData = ComponentAndroidVariantData(variant)
        publisher.publish(androidVariantData)
    }
}