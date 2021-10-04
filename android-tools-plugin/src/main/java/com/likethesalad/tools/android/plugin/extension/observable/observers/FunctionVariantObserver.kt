package com.likethesalad.tools.android.plugin.extension.observable.observers

import com.likethesalad.tools.android.plugin.data.AndroidVariantData
import com.likethesalad.tools.android.plugin.extension.observable.VariantObserver

class FunctionVariantObserver(private val function: (AndroidVariantData) -> Unit) : VariantObserver {

    override fun onUpdate(variantData: AndroidVariantData) {
        function.invoke(variantData)
    }
}