package com.likethesalad.tools.agpcompat.api

import com.likethesalad.tools.agpcompat.api.utilities.AgpVersion
import org.gradle.api.Project
import org.gradle.api.logging.Logging
import java.util.ServiceLoader


abstract class AgpCompatibilityEntrypoint {
    abstract fun getDescription(): String

    abstract fun isCompatible(currentVersion: AgpVersion?): Boolean

    abstract fun providePluginStrategy(project: Project): PluginStrategy

    companion object {
        @JvmStatic
        fun findCompatibleStrategy(project: Project): PluginStrategy {
            val entrypoints = ServiceLoader.load(AgpCompatibilityEntrypoint::class.java)
            check(
                entrypoints.iterator().hasNext()
            ) { "No implementations found for " + AgpCompatibilityEntrypoint::class.java.name }
            val currentVersion = getCurrentAgpVersion(project)
            for (entrypoint in entrypoints) {
                if (entrypoint.isCompatible(currentVersion)) {
                    Logging.getLogger(AgpCompatibilityEntrypoint::class.java)
                        .debug("Found AGP compatible entrypoint with description: '{}'", entrypoint.getDescription())
                    return entrypoint.providePluginStrategy(project)
                }
            }
            throw UnsupportedOperationException("Could not find compatible strategy for agp version $currentVersion")
        }

        private fun getCurrentAgpVersion(project: Project): AgpVersion? {
            return try {
                val componentsExtensionType = Class.forName("com.android.build.api.variant.AndroidComponentsExtension")
                val componentsExtension = project.extensions.findByType(componentsExtensionType)
                val pluginVersionGetter = componentsExtensionType.getDeclaredMethod("getPluginVersion")
                val currentVersion = pluginVersionGetter.invoke(componentsExtension)
                androidPluginVersionToAgpVersion(currentVersion)
            } catch (e: ClassNotFoundException) {
                null
            }
        }

        private fun androidPluginVersionToAgpVersion(currentVersion: Any): AgpVersion {
            val majorGetter = currentVersion.javaClass.getDeclaredMethod("getMajor")
            val minorGetter = currentVersion.javaClass.getDeclaredMethod("getMinor")
            val microGetter = currentVersion.javaClass.getDeclaredMethod("getMicro")
            return AgpVersion(
                majorGetter.invoke(currentVersion) as Int,
                minorGetter.invoke(currentVersion) as Int,
                microGetter.invoke(currentVersion) as Int
            )
        }
    }
}