package com.likethesalad.tools.agpcompat74

import com.likethesalad.tools.agpcompat.api.AgpCompatibilityEntrypoint
import com.likethesalad.tools.agpcompat.api.PluginStrategy
import com.likethesalad.tools.agpcompat.api.utilities.AgpVersion
import com.likethesalad.tools.agpcompat74.strategies.PluginStrategy74
import org.gradle.api.Project

class AgpCompatibilityEntrypoint74 : AgpCompatibilityEntrypoint() {

    override fun getDescription(): String {
        return "AGP compatibility for >= 7.4.0"
    }

    override fun isCompatible(currentVersion: AgpVersion?): Boolean {
        if (currentVersion == null) {
            return false
        }
        return currentVersion >= AgpVersion(7, 4, 0)
    }

    override fun providePluginStrategy(project: Project): PluginStrategy {
        return PluginStrategy74()
    }
}