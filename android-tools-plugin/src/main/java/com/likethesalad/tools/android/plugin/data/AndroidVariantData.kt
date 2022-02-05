package com.likethesalad.tools.android.plugin.data

import org.gradle.api.artifacts.Configuration
import org.gradle.api.file.DirectoryProperty

interface AndroidVariantData {
    fun getVariantName(): String
    fun getVariantType(): String
    fun getVariantFlavors(): List<String>
    fun getRuntimeConfiguration(): Configuration
    fun registerGeneratedJavaResources(outputDir: DirectoryProperty)
    fun registerGeneratedJavaBinaries(outputDir: DirectoryProperty)
}