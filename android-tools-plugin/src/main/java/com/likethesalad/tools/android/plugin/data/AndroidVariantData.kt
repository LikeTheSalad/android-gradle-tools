package com.likethesalad.tools.android.plugin.data

import org.gradle.api.Task
import org.gradle.api.file.Directory
import org.gradle.api.file.FileCollection
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.AbstractCopyTask
import org.gradle.api.tasks.TaskProvider

interface AndroidVariantData {
    fun getVariantName(): String
    fun getVariantType(): String
    fun getVariantFlavors(): List<String>
    fun getLibrariesResources(): FileCollection
    fun getProcessJavaResourcesProvider(): TaskProvider<AbstractCopyTask>
    fun registerGeneratedJavaBinaries(generator: TaskProvider<out Task>, outputDir: Provider<Directory>)
}