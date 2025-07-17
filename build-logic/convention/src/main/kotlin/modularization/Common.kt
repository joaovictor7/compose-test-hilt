package modularization

import appconfig.AppConfig
import extension.getLibrary
import extension.ksp
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.commonConfigurations() {
    with(pluginManager) {
        apply("com.google.devtools.ksp")
    }
    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(AppConfig.JDK_TARGET))
        }
    }
    dependencies {
        ksp(getLibrary("android.hilt.compiler"))
    }
}