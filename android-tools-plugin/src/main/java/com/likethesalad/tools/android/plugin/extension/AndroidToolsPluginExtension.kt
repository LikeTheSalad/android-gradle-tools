package com.likethesalad.tools.android.plugin.extension

import com.likethesalad.tools.agpcompat.api.bridges.AndroidExtension
import com.likethesalad.tools.agpcompat.api.bridges.AndroidVariantData
import com.likethesalad.tools.agpcompat.api.observable.VariantPublisher
import com.likethesalad.tools.agpcompat.api.observable.observers.FunctionVariantObserver

open class AndroidToolsPluginExtension(
    private val publisher: VariantPublisher
) {
    lateinit var androidExtension: AndroidExtension

    fun onVariant(function: (AndroidVariantData) -> Unit) {
        publisher.register(FunctionVariantObserver(function))
    }
}