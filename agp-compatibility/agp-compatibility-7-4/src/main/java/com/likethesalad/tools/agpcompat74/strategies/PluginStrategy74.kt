package com.likethesalad.tools.agpcompat74.strategies

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.Variant
import com.likethesalad.tools.agpcompat.api.PluginStrategy
import com.likethesalad.tools.agpcompat.api.bridges.AndroidExtension
import com.likethesalad.tools.agpcompat.api.observable.VariantPublisher
import com.likethesalad.tools.agpcompat74.bridges.AndroidExtension74
import com.likethesalad.tools.agpcompat74.bridges.AndroidVariantData74
import org.gradle.api.Project

class PluginStrategy74 : PluginStrategy {

    override fun configure(project: Project, publisher: VariantPublisher): AndroidExtension {
        val androidExtension = project.extensions.getByType(CommonExtension::class.java)
        val componentsExtension = project.extensions.getByType(AndroidComponentsExtension::class.java)

        componentsExtension.onVariants {
            addVariantData(it, publisher)
        }

        return AndroidExtension74(androidExtension)
    }

    private fun addVariantData(variant: Variant, publisher: VariantPublisher) {
        val androidVariantData = AndroidVariantData74(variant)
        publisher.publish(androidVariantData)
    }
}