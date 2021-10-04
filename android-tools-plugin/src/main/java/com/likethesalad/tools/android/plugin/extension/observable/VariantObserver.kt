package com.likethesalad.tools.android.plugin.extension.observable

import com.likethesalad.tools.android.plugin.data.AndroidVariantData

interface VariantObserver {
    fun onUpdate(variantData: AndroidVariantData)
}
