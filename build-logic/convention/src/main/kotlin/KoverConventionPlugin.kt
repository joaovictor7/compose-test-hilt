import extension.findVersion
import extension.isKotlin
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class KoverConventionPlugin : Plugin<Project> {

    private val Project.koverDirectoryByModule
        get() = project.takeIf {
            it != project.rootProject
        }?.path?.replace(":", "/") ?: project.name

    private val Project.variantName
        get() = if (isKotlin) {
            "jvm"
        } else {
            "developDebug"
        }

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlinx.kover")
            }
            extensions.configure<KoverProjectExtension> {
                useJacoco(findVersion("jacoco"))
                currentProject {
                    createVariant(KOVER_VARIANT_NAME_REPORT) {
                        add(variantName)
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
                            classes("com.composetest.*Extensions")
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
        const val KOVER_VARIANT_NAME_REPORT = "all"
    }
}