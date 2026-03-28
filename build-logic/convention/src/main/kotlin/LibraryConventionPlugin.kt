import appconfig.AppConfig
import com.android.build.api.dsl.LibraryExtension
import modularization.configureAndroid
import modularization.setLibraryBuildTypes
import modularization.setLibraryFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    minSdk = AppConfig.MIN_SDK_VERSION
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                packaging {
                    resources {
                        excludes += "/META-INF/LICENSE*"
                    }
                }
                setLibraryBuildTypes(this)
                setLibraryFlavors(this)
                configureAndroid(this)
            }
        }
    }
}