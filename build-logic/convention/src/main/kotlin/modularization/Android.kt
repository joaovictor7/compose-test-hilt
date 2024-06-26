package modularization

import appconfig.AppConfig
import com.android.build.gradle.BaseExtension
import extensions.getLibrary
import extensions.implementation
import extensions.ksp
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroid() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.android")
        apply("org.jetbrains.kotlin.plugin.serialization")
        apply("com.google.devtools.ksp")
    }
    configure<BaseExtension> {
        compileSdkVersion(AppConfig.COMPILE_SDK_VERSION)
        defaultConfig {
            minSdk = AppConfig.MIN_SDK_VERSION
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_19
            targetCompatibility = JavaVersion.VERSION_19
        }
    }
    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_19)
        }
    }
    setBuildTypesAllModules()
    dependencies {
        implementation(platform(getLibrary("firebase.bom")))
        implementation(getLibrary("androidx.lifecycle.runtime.ktx"))
        implementation(getLibrary("kotlin.coroutines.android"))
        implementation(getLibrary("kotlin.json.serializable"))
        implementation(getLibrary("android.hilt"))
        ksp(getLibrary("android.hilt.compiler"))
    }
}