package com.likethesalad.tools.agpcompat.api.observable

import com.likethesalad.tools.agpcompat.api.bridges.AndroidVariantData

interface VariantObserver {
    fun onUpdate(variantData: AndroidVariantData)
}
