package com.likethesalad.tools.android.plugin.base

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.OutputDirectory

abstract class BaseJavaBytecodeGeneratorTask : DefaultTask() {

    @OutputDirectory
    val outputDir: DirectoryProperty = project.objects.directoryProperty()
}