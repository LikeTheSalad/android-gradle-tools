package com.likethesalad.tools.agpcompat.api

import com.likethesalad.tools.agpcompat.api.bridges.AndroidExtension
import com.likethesalad.tools.agpcompat.api.observable.VariantPublisher
import org.gradle.api.Project

interface PluginStrategy {
    fun configure(project: Project, publisher: VariantPublisher): AndroidExtension
}