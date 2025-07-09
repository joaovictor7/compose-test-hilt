package modularization

import appconfig.AppConfig
import com.android.build.api.dsl.CommonExtension
import extension.getLibrary
import extension.implementation
import extension.ksp
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) = with(commonExtension) {
    with(pluginManager) {
        apply("com.composetest.kover")
        apply("com.google.dagger.hilt.android")
        apply("com.google.devtools.ksp")
        apply("org.jetbrains.kotlin.android")
        apply("org.jetbrains.kotlin.plugin.serialization")
        apply("kotlin-parcelize")
    }
    compileSdk = AppConfig.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = AppConfig.MIN_SDK_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(AppConfig.JDK_TARGET))
        }
    }
    extensions.configure<KotlinAndroidProjectExtension> {
        jvmToolchain(AppConfig.JDK_TARGET)
    }
    packaging {
        resources {
            excludes += "/META-INF/LICENSE*"
        }
    }
//    experimentalProperties["android.experimental.enableScreenshotTest"] = true //~> revert after update
    setBuildTypes()
    setFlavors()
    dependencies {
        implementation(getLibrary("androidx.lifecycle.runtime.ktx"))
        implementation(getLibrary("kotlin.coroutines.android"))
        implementation(getLibrary("android.hilt"))
        ksp(getLibrary("android.hilt.compiler"))
    }
}