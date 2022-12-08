package com.likethesalad.tools.android.plugin.version

interface AgpComponentsHelper {

    fun isVersionGreaterOrEqualTo(major: Int, minor: Int): Boolean
}