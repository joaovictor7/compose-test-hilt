import appconfig.AppConfig
import extension.findVersion
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class KoverConventionPlugin : Plugin<Project> {

    private val Project.koverDirectoryByModule
        get() = project.takeIf {
            it != project.rootProject
        }?.path?.replace(":", "/") ?: project.name

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlinx.kover")
            }
            extensions.configure<KoverProjectExtension> {
                useJacoco(findVersion("jacoco"))
                currentProject {
                    createVariant(KOVER_VARIANT_NAME_REPORT) {
                        if (name != AppConfig.PROJECT_NAME) {
                            add("developDebug")
                        }
                    }
                }
                if (name == AppConfig.PROJECT_NAME) {
                    merge {
                        allProjects()
                        createVariant(KOVER_VARIANT_NAME_REPORT) { }
                    }
                }
                reports {
                    variant(KOVER_VARIANT_NAME_REPORT) {
                        html {
                            htmlDir.set(file("$rootDir/kover/$koverDirectoryByModule"))
                        }
                    }
                    filters {
                        excludes {
                            androidGeneratedClasses()
                        }
                        includes {
                            classes("com.composetest.*ViewModel")
                            classes("com.composetest.*UseCase")
                            classes("com.composetest.*Repository")
                        }
                    }
                }
            }
        }
    }

    private companion object {
        const val KOVER_VARIANT_NAME_REPORT = "project"
    }
}