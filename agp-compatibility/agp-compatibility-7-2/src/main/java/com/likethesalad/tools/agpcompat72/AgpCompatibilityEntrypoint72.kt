package com.likethesalad.tools.agpcompat72

import com.likethesalad.tools.agpcompat.api.AgpCompatibilityEntrypoint
import com.likethesalad.tools.agpcompat.api.PluginStrategy
import com.likethesalad.tools.agpcompat.api.utilities.AgpVersion
import com.likethesalad.tools.agpcompat72.strategies.PluginStrategy72
import org.gradle.api.Project

class AgpCompatibilityEntrypoint72 : AgpCompatibilityEntrypoint() {

    override fun getDescription(): String {
        return "AGP compatibility for <= 7.2.0"
    }

    override fun isCompatible(currentVersion: AgpVersion?): Boolean {
        if (currentVersion == null) {
            return true
        }
        return currentVersion < AgpVersion(7, 3, 0)
    }

    override fun providePluginStrategy(project: Project): PluginStrategy {
        return PluginStrategy72()
    }
}