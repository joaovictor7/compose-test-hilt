import appconfig.AppConfig
import appconfig.AppSigning
import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationExtension
import extensions.getLibrary
import extensions.implementation
import modularization.configureAndroid
import modularization.setApplicationBuildTypes
import modularization.setFlavors
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.util.Properties

internal class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.google.dagger.hilt.android")
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
            }
            extensions.configure<ApplicationExtension> {
                configureAndroid(this)
                defaultConfig {
                    versionCode = AppConfig.CODE_VERSION
                    versionName = AppConfig.NAME_VERSION
                    targetSdk = AppConfig.TARGET_SDK_VERSION
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
                signingConfigs {
                    createSigning(this, AppSigning.RELEASE)
                }
                buildFeatures {
                    buildConfig = true
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
                setApplicationBuildTypes()
                setFlavors()
            }
            dependencies {
                implementation(getLibrary("firebase.analytics"))
                implementation(getLibrary("firebase.crashlytics"))
            }
        }
    }

    private fun Project.createSigning(
        apkSigningConfig: NamedDomainObjectContainer<out ApkSigningConfig>,
        name: AppSigning
    ) {
        val propertyFile = file("../${name.signingName}-signing.properties")
        if (propertyFile.exists()) {
            val property = Properties().apply { propertyFile.inputStream().use { load(it) } }
            apkSigningConfig.create(name.signingName) {
                storeFile = file("../keys/${name.signingName}/key-chain")
                storePassword = property.getProperty("key.store.password")
                keyAlias = property.getProperty("key.alias")
                keyPassword = property.getProperty("key.alias.password")
            }
        }
    }
}