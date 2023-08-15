package com.likethesalad.tools.agpcompat.api.bridges

import com.likethesalad.tools.agpcompat.api.tasks.DirProducerTask
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
        generator: TaskProvider<out DirProducerTask>,
        outputDir: Provider<Directory>
    )
}