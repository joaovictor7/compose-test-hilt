import extension.getLibrary
import extension.testImplementation
import extension.testRuntimeOnly
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

internal class TestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.composetest.kover")
            }
            tasks.withType<Test> {
                ignoreFailures = true
                useJUnitPlatform()
                jvmArgs("-Xshare:off")
                jvmArgs("-XX:+EnableDynamicAgentLoading")
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