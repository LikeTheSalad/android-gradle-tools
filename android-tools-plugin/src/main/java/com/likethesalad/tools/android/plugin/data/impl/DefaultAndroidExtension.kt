package com.likethesalad.tools.android.plugin.data.impl

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.AndroidSourceDirectorySet
import com.likethesalad.tools.android.plugin.data.AndroidExtension
import java.io.File

class DefaultAndroidExtension(private val extension: BaseExtension) : AndroidExtension {

    override fun getVariantSrcDirs(variantName: String): Set<File> {
        return getVariantRes(variantName).srcDirs
    }

    override fun setVariantSrcDirs(variantName: String, dirs: Set<File>) {
        getVariantRes(variantName).setSrcDirs(dirs)
    }

    override fun addVariantSrcDir(variantName: String, dir: Any) {
        getVariantRes(variantName).srcDir(dir)
    }

    private fun getVariantRes(variantName: String): AndroidSourceDirectorySet {
        return extension.sourceSets.getByName(variantName).res
    }
}