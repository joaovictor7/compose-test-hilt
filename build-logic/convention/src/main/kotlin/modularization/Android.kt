package modularization

import appconfig.AppConfig
import com.android.build.api.dsl.CommonExtension
import extension.getLibrary
import extension.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) = with(commonExtension) {
    commonConfigurations()
    with(pluginManager) {
        apply("kotlin-parcelize")
        apply("org.jetbrains.kotlin.android")
    }
    compileSdk = AppConfig.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = AppConfig.MIN_SDK_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/LICENSE*"
        }
    }
    extensions.configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_22)
        }
    }
//    experimentalProperties["android.experimental.enableScreenshotTest"] = true //~> revert after update
    dependencies {
        implementation(getLibrary("androidx.lifecycle.runtime.ktx"))
    }
}