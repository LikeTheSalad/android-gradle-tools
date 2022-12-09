package com.likethesalad.tools.android.plugin.version

import org.gradle.api.Project

object AgpApiHelper {

    fun isLegacyApi(project: Project): Boolean {
        if (!isAndroidComponentsAvailable()) {
            return true
        }
        val componentsHelper = Class.forName("com.likethesalad.tools.android.plugin.version.AgpComponentsHelperImpl")
            .getDeclaredConstructor(Project::class.java).newInstance(project) as AgpComponentsHelper
        return componentsHelper.isVersionLowerThan(7, 3)
    }

    private fun isAndroidComponentsAvailable(): Boolean {
        return try {
            Class.forName("com.android.build.api.variant.AndroidComponentsExtension", false, javaClass.classLoader)
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }
}