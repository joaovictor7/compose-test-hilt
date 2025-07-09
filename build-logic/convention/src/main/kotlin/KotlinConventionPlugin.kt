import appconfig.AppConfig
import modularization.commonConfigurations
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal class KotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
            }
            extensions.configure<KotlinJvmProjectExtension> {
                jvmToolchain(AppConfig.KOTLIN_JVM_TARGET)
            }
            commonConfigurations()
        }
    }
}