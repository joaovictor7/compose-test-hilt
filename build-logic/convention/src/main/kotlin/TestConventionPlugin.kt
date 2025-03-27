import extensions.findVersion
import extensions.getLibrary
import extensions.testImplementation
import extensions.testRuntimeOnly
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

internal class TestConventionPlugin : Plugin<Project> {

    private val Project.koverDirectoryByModule
        get() = project.takeIf {
            it != project.rootProject
        }?.path?.replace(":", "/") ?: project.name

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlinx.kover")
            }
            tasks.withType<Test> {
                ignoreFailures = true
                useJUnitPlatform()
                jvmArgs("-Xshare:off")
                jvmArgs("-XX:+EnableDynamicAgentLoading")
            }
            extensions.configure<KoverProjectExtension> {
                useJacoco(findVersion("jacoco"))
                currentProject {
                    createVariant("project") {
                        add("developDebug")
                    }
                }
                reports {
                    variant("project") {
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
            dependencies {
                testImplementation(project(":core:test"))
                testImplementation(getLibrary("junit5"))
                testImplementation(getLibrary("mockk"))
                testImplementation(getLibrary("kotlin.coroutines.test"))
                testImplementation(getLibrary("slf4j.simple"))
                testRuntimeOnly(getLibrary("junit5.engine"))
                testRuntimeOnly(getLibrary("junit.launcher"))
            }
        }
    }
}