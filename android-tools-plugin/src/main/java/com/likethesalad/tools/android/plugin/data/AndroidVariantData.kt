package com.likethesalad.tools.android.plugin.data

import com.likethesalad.tools.android.plugin.base.BaseJavaBytecodeGeneratorTask
import org.gradle.api.file.Directory
import org.gradle.api.file.FileCollection
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.TaskProvider

interface AndroidVariantData {
    fun getVariantName(): String
    fun getVariantType(): String
    fun getVariantFlavors(): List<String>
    fun getLibrariesResources(): FileCollection
    fun getLibrariesJars(): FileCollection
    fun registerGeneratedJavaBinaries(
        generator: TaskProvider<out BaseJavaBytecodeGeneratorTask>,
        outputDir: Provider<Directory>
    )
}