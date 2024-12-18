import appconfig.AppConfig
import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationExtension
import enums.Signing
import extensions.getLibrary
import extensions.implementation
import modularization.configureAndroid
import modularization.setBuildTypes
import modularization.setDefaultBuildConfigFields
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
                    manifestPlaceholders["appName"] = AppConfig.APP_NAME
                    manifestPlaceholders["usesCleartextTraffic"] = false
                    setDefaultBuildConfigFields(project)
                }
                signingConfigs {
                    createSigning(this, Signing.RELEASE)
                }
                buildFeatures {
                    buildConfig = true
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
                setBuildTypes(true)
                setFlavors(true)
            }
            dependencies {
                implementation(getLibrary("firebase.analytics"))
                implementation(getLibrary("firebase.crashlytics"))
            }
        }
    }

    private fun Project.createSigning(
        apkSigningConfig: NamedDomainObjectContainer<out ApkSigningConfig>,
        name: Signing
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