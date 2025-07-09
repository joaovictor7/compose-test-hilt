package modularization

import appconfig.AppConfig
import extension.getLibrary
import extension.implementation
import extension.ksp
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.commonConfigurations() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.plugin.serialization")
        apply("com.google.dagger.hilt.android")
        apply("com.google.devtools.ksp")
    }
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = AppConfig.javaVersion
        targetCompatibility = AppConfig.javaVersion
    }
    dependencies {
        implementation(getLibrary("kotlin.coroutines.android"))
        implementation(getLibrary("android.hilt"))
        ksp(getLibrary("android.hilt.compiler"))
    }
}