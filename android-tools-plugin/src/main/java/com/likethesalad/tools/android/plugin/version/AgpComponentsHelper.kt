package com.likethesalad.tools.android.plugin.version

interface AgpComponentsHelper {

    fun isVersionLowerThan(major: Int, minor: Int): Boolean
}