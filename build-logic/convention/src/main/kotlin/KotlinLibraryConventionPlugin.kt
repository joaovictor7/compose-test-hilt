import appconfig.AppConfig
import extension.getLibrary
import extension.implementation
import modularization.commonConfigurations
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
            }
            extensions.configure<KotlinJvmProjectExtension> {
                jvmToolchain(AppConfig.JDK_TARGET)
            }
            commonConfigurations()
            dependencies {
                implementation(getLibrary("kotlin.coroutines.core"))
                implementation(getLibrary("android.hilt.core"))
            }
        }
    }
}