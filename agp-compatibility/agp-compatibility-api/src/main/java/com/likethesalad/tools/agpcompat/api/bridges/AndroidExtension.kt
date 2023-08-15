package com.likethesalad.tools.agpcompat.api.bridges

import java.io.File

interface AndroidExtension {
    fun getVariantSrcDirs(variantName: String): Set<File>
    fun setVariantSrcDirs(variantName: String, dirs: Set<File>)
    fun addVariantSrcDir(variantName: String, dir: Any)
}