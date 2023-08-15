package com.likethesalad.tools.agpcompat73.strategies

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.Variant
import com.likethesalad.tools.agpcompat.api.PluginStrategy
import com.likethesalad.tools.agpcompat.api.bridges.AndroidExtension
import com.likethesalad.tools.agpcompat.api.observable.VariantPublisher
import com.likethesalad.tools.agpcompat73.bridges.AndroidExtension73
import com.likethesalad.tools.agpcompat73.bridges.AndroidVariantData73
import org.gradle.api.Project

class PluginStrategy73 : PluginStrategy {

    override fun configure(project: Project, publisher: VariantPublisher): AndroidExtension {
        val androidExtension = project.extensions.getByType(CommonExtension::class.java)
        val componentsExtension = project.extensions.getByType(AndroidComponentsExtension::class.java)

        componentsExtension.onVariants {
            addVariantData(it, publisher)
        }

        return AndroidExtension73(androidExtension)
    }

    private fun addVariantData(variant: Variant, publisher: VariantPublisher) {
        val androidVariantData = AndroidVariantData73(variant)
        publisher.publish(androidVariantData)
    }
}