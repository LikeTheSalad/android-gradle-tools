package com.likethesalad.tools.android.plugin.data.impl

import com.android.build.gradle.api.BaseVariant
import com.likethesalad.tools.android.plugin.data.AndroidVariantData
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.ArtifactView
import org.gradle.api.attributes.Attribute
import org.gradle.api.file.Directory
import org.gradle.api.file.FileCollection
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.AbstractCopyTask
import org.gradle.api.tasks.TaskProvider

class DefaultAndroidVariantData(
    private val project: Project,
    private val variant: BaseVariant
) : AndroidVariantData {

    companion object {
        private val artifactTypeAttr = Attribute.of("artifactType", String::class.java)
    }

    override fun getVariantName(): String = variant.name

    override fun getVariantType(): String = variant.buildType.name

    override fun getVariantFlavors(): List<String> = variant.productFlavors.map { it.name }

    override fun getLibrariesResources(): FileCollection {
        return getFilesFromConfiguration("android-res")
    }

    override fun getLibrariesJars(): FileCollection {
        return getFilesFromConfiguration("jar")
    }

    override fun getProcessJavaResourcesProvider(): TaskProvider<AbstractCopyTask> {
        return variant.processJavaResourcesProvider
    }

    override fun registerGeneratedJavaBinaries(generator: TaskProvider<out Task>, outputDir: Provider<Directory>) {
        val files = project.files(outputDir).builtBy(generator)
        variant.registerPreJavacGeneratedBytecode(files)
    }

    private fun getFilesFromConfiguration(artifactType: String): FileCollection {
        return variant.runtimeConfiguration.incoming
            .artifactView(getAndroidArtifactViewAction(artifactType))
            .artifacts
            .artifactFiles
    }

    private fun getAndroidArtifactViewAction(artifactType: String): Action<ArtifactView.ViewConfiguration> {
        return Action { config ->
            config.isLenient = false
            config.attributes {
                it.attribute(artifactTypeAttr, artifactType)
            }
        }
    }
}