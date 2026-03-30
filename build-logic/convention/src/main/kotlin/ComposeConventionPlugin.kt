import com.android.build.api.dsl.CommonExtension
import extension.debugImplementation
import extension.getLibrary
import extension.implementation
import extension.screenshotTestImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.android.compose.screenshot")
            }
            extensions.configure<CommonExtension> {
                buildFeatures.compose = true
            }
            dependencies {
                implementation(platform(getLibrary("compose.bom")))
                implementation(getLibrary("compose.activity"))
                implementation(getLibrary("compose.ui.tooling.preview"))
                implementation(getLibrary("compose.material3"))
                implementation(getLibrary("navigation3.runtime"))
                implementation(getLibrary("navigation3.ui"))
                implementation(getLibrary("lifecycle.viewmodel.navigation3"))
                implementation(getLibrary("androidx.lifecycle.runtime.compose"))
                implementation(getLibrary("androidx.hilt.navigation.compose"))
                debugImplementation(getLibrary("compose.ui.tooling"))
                screenshotTestImplementation(getLibrary("compose.ui.tooling"))
                screenshotTestImplementation(getLibrary("android.screenshot.validation"))
            }
        }
    }
}