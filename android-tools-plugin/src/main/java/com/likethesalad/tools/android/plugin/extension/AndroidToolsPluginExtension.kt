package com.likethesalad.tools.android.plugin.extension

import com.likethesalad.tools.android.plugin.data.AndroidExtension
import com.likethesalad.tools.android.plugin.data.AndroidVariantData
import com.likethesalad.tools.android.plugin.extension.observable.VariantPublisher
import com.likethesalad.tools.android.plugin.extension.observable.observers.FunctionVariantObserver
import org.gradle.api.model.ObjectFactory

open class AndroidToolsPluginExtension(
    objectFactory: ObjectFactory,
    private val publisher: VariantPublisher
) {
    val androidExtension = objectFactory.property(AndroidExtension::class.java)

    fun onVariant(function: (AndroidVariantData) -> Unit) {
        publisher.register(FunctionVariantObserver(function))
    }
}