package modularization

import appconfig.AppConfig
import com.android.build.api.dsl.CommonExtension
import extensions.getLibrary
import extensions.implementation
import extensions.ksp
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) = with(commonExtension) {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.android")
        apply("com.google.devtools.ksp")
    }
    compileSdk = AppConfig.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = AppConfig.MIN_SDK_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_22
        targetCompatibility = JavaVersion.VERSION_22
    }
    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_22)
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/LICENSE*"
        }
    }
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
    dependencies {
        implementation(platform(getLibrary("firebase.bom")))
        implementation(getLibrary("androidx.lifecycle.runtime.ktx"))
        implementation(getLibrary("kotlin.coroutines.android"))
        implementation(getLibrary("android.hilt"))
        ksp(getLibrary("android.hilt.compiler"))
    }
}