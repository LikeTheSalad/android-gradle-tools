package com.likethesalad.tools.agpcompat.api.observable.observers

import com.likethesalad.tools.agpcompat.api.bridges.AndroidVariantData
import com.likethesalad.tools.agpcompat.api.observable.VariantObserver

class FunctionVariantObserver(private val function: (AndroidVariantData) -> Unit) : VariantObserver {

    override fun onUpdate(variantData: AndroidVariantData) {
        function.invoke(variantData)
    }
}