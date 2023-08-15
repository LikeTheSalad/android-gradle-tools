package com.likethesalad.tools.agpcompat72.bridges

import com.android.build.gradle.api.BaseVariant
import com.likethesalad.tools.agpcompat.api.bridges.AndroidVariantData
import com.likethesalad.tools.agpcompat.api.tasks.DirProducerTask
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.ArtifactView
import org.gradle.api.attributes.Attribute
import org.gradle.api.file.Directory
import org.gradle.api.file.FileCollection
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.TaskProvider

class AndroidVariantData72(
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
        return getFilesFromConfiguration("android-classes-jar")
    }

    override fun registerGeneratedJavaBinaries(
        generator: TaskProvider<out DirProducerTask>,
        outputDir: Provider<Directory>
    ) {
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