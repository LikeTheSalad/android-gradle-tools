package com.likethesalad.tools.android.plugin.data.impl.componentextension

import com.android.build.api.artifact.MultipleArtifact
import com.android.build.api.variant.Variant
import com.likethesalad.tools.android.plugin.base.BaseJavaBytecodeGeneratorTask
import com.likethesalad.tools.android.plugin.data.AndroidVariantData
import org.gradle.api.Action
import org.gradle.api.artifacts.ArtifactView
import org.gradle.api.attributes.Attribute
import org.gradle.api.file.Directory
import org.gradle.api.file.FileCollection
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.TaskProvider

@Suppress("UnstableApiUsage")
class ComponentAndroidVariantData(private val variant: Variant) : AndroidVariantData {

    companion object {
        private val artifactTypeAttr = Attribute.of("artifactType", String::class.java)
    }

    override fun getVariantName(): String = variant.name

    override fun getVariantType(): String = variant.buildType!!

    override fun getVariantFlavors(): List<String> {
        return variant.productFlavors.map { it.second }
    }

    override fun getLibrariesResources(): FileCollection {
        return getFilesFromConfiguration("android-res")
    }

    override fun getLibrariesJars(): FileCollection {
        return getFilesFromConfiguration("android-classes-jar")
    }

    override fun registerGeneratedJavaBinaries(
        generator: TaskProvider<out BaseJavaBytecodeGeneratorTask>,
        outputDir: Provider<Directory>
    ) {
        variant.artifacts.use(generator)
            .wiredWith(BaseJavaBytecodeGeneratorTask::outputDir)
            .toAppendTo(MultipleArtifact.ALL_CLASSES_DIRS)
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