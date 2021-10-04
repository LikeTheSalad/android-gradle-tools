package com.likethesalad.tools.android.plugin.data

import org.gradle.api.artifacts.Configuration

interface AndroidVariantData {
    fun getVariantName(): String
    fun getVariantType(): String
    fun getVariantFlavors(): List<String>
    fun getRuntimeConfiguration(): Configuration
}