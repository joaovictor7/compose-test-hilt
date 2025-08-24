import appconfig.AppConfig
import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationExtension
import enums.Signing
import extension.getLibrary
import extension.implementation
import file.LoadPropertiesFile
import file.PropertiesFile
import modularization.configureAndroid
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val propertiesFile = LoadPropertiesFile(this)
        with(pluginManager) {
            apply("com.android.application")
            apply("com.google.gms.google-services")
            apply("com.google.firebase.crashlytics")
        }
        extensions.configure<ApplicationExtension> {
            defaultConfig {
                versionCode = AppConfig.CODE_VERSION
                versionName = AppConfig.NAME_VERSION
                targetSdk = AppConfig.TARGET_SDK_VERSION
                vectorDrawables {
                    useSupportLibrary = true
                }
                manifestPlaceholders["appName"] = AppConfig.APP_NAME
                manifestPlaceholders["usesCleartextTraffic"] = false
                manifestPlaceholders["mapsApiKey"] = propertiesFile.getProperty("key.maps")
            }
            signingConfigs {
                createSignings(this)
            }
            buildFeatures {
                buildConfig = true
            }
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
            configureAndroid(this)
        }
        dependencies {
            implementation(getLibrary("firebase.analytics"))
            implementation(getLibrary("firebase.crashlytics"))
        }
    }
}

private fun Project.createSignings(
    apkSigningConfig: NamedDomainObjectContainer<out ApkSigningConfig>
) = with(apkSigningConfig) {
    Signing.entries.forEach { signing ->
        val propertiesFile = PropertiesFile.SigningKey(rootDir, signing)
        val properties = LoadPropertiesFile(this@createSignings, propertiesFile)
        create(signing.toString()) {
            storeFile = file(propertiesFile.keyPath)
            storePassword = properties.getProperty("key.store.password")
            keyAlias = properties.getProperty("key.alias")
            keyPassword = properties.getProperty("key.alias.password")
        }
    }
}