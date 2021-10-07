package com.likethesalad.tools.android.plugin.extension

import com.likethesalad.tools.android.plugin.data.AndroidExtension
import com.likethesalad.tools.android.plugin.data.AndroidVariantData
import com.likethesalad.tools.android.plugin.extension.observable.VariantPublisher
import com.likethesalad.tools.android.plugin.extension.observable.observers.FunctionVariantObserver

open class AndroidToolsPluginExtension(
    private val publisher: VariantPublisher
) {
    lateinit var androidExtension: AndroidExtension

    fun onVariant(function: (AndroidVariantData) -> Unit) {
        publisher.register(FunctionVariantObserver(function))
    }
}