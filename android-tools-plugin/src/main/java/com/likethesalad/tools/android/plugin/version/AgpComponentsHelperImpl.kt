package com.likethesalad.tools.android.plugin.version

import com.android.build.api.AndroidPluginVersion
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Project

class AgpComponentsHelperImpl(private val project: Project) : AgpComponentsHelper {

    override fun isVersionGreaterOrEqualTo(major: Int, minor: Int): Boolean {
        val other = AndroidPluginVersion(major, minor)
        return getAndroidComponentsExtension().pluginVersion >= other
    }

    private fun getAndroidComponentsExtension(): AndroidComponentsExtension<*, *, *> {
        return project.extensions.getByType(AndroidComponentsExtension::class.java)
    }
}