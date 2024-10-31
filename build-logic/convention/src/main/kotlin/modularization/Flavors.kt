package modularization

import appconfig.AppConfig
import appconfig.AppFlavor
import appconfig.AppFlavorDimension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.setFlavors(application: Boolean) = extensions.configure<BaseExtension> {
    flavorDimensions(*AppFlavorDimension.allDimensions.toTypedArray())
    productFlavors {
        AppFlavorDimension.values().forEach { dimension ->
            dimension.flavors.forEach { flavor ->
                create(flavor.toString()) {
                    this.dimension = dimension.toString()
                    this.isDefault = flavor.isDefault
                    if (application) {
                        setApplicationIdSuffix(dimension, flavor)
                        setManifestPlaceholders(dimension, flavor)
                        setBuildConfigFields(dimension, flavor)
                    }
                }
            }
        }
    }
}

private fun ApplicationProductFlavor.setApplicationIdSuffix(
    dimension: AppFlavorDimension,
    flavor: AppFlavor
) {
    if (dimension == AppFlavorDimension.ENVIRONMENT && flavor != AppFlavor.PRODUCTION) {
        versionNameSuffix = "-$flavor"
        applicationIdSuffix = ".$flavor"
    }
}

private fun ApplicationProductFlavor.setManifestPlaceholders(
    dimension: AppFlavorDimension,
    flavor: AppFlavor
) {
    if (dimension == AppFlavorDimension.ENVIRONMENT && flavor != AppFlavor.PRODUCTION) {
        manifestPlaceholders["appName"] = "${AppConfig.APP_NAME} - ${flavor.name}"
        manifestPlaceholders["usesCleartextTraffic"] = true
    }
}