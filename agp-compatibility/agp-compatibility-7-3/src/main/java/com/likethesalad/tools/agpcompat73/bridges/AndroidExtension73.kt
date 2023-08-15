package com.likethesalad.tools.agpcompat73.bridges

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.api.DefaultAndroidSourceDirectorySet
import com.likethesalad.tools.agpcompat.api.bridges.AndroidExtension
import java.io.File

class AndroidExtension73(private val androidExtension: CommonExtension<*, *, *, *>) : AndroidExtension {

    override fun getVariantSrcDirs(variantName: String): Set<File> {
        return getVariantRes(variantName).srcDirs
    }

    override fun setVariantSrcDirs(variantName: String, dirs: Set<File>) {
        getVariantRes(variantName).setSrcDirs(dirs)
    }

    override fun addVariantSrcDir(variantName: String, dir: Any) {
        getVariantRes(variantName).srcDir(dir)
    }

    private fun getVariantRes(variantName: String): DefaultAndroidSourceDirectorySet {
        return androidExtension.sourceSets.getByName(variantName).res as DefaultAndroidSourceDirectorySet
    }
}