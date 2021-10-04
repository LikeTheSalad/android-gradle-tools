package com.likethesalad.tools.android.plugin.extension.observable

import com.likethesalad.tools.android.plugin.data.AndroidVariantData

class VariantPublisher {

    private val publishedVariants = mutableListOf<AndroidVariantData>()
    private val observers = mutableListOf<VariantObserver>()

    fun register(observer: VariantObserver) = synchronized(this) {
        observers.add(observer)
        publishedVariants.forEach {
            observer.onUpdate(it)
        }
    }

    fun publish(variant: AndroidVariantData) = synchronized(this) {
        observers.forEach {
            it.onUpdate(variant)
        }
        publishedVariants.add(variant)
    }
}