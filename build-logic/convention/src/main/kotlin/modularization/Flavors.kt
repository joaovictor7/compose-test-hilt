package modularization

import appconfig.AppConfig
import enums.Flavor
import enums.FlavorDimension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.setFlavors(application: Boolean) = extensions.configure<BaseExtension> {
    flavorDimensions(*FlavorDimension.allDimensions.toTypedArray())
    productFlavors {
        FlavorDimension.values().forEach { dimension ->
            dimension.flavors.forEach { flavor ->
                create(flavor.toString()) {
                    this.dimension = dimension.toString()
                    this.isDefault = flavor.isDefault
                    if (application) {
                        setNonProductionFields(dimension, flavor)
                        setBuildConfigFields(this@setFlavors, dimension, flavor)
                    }
                }
            }
        }
    }
}

private fun ApplicationProductFlavor.setNonProductionFields(dimension: FlavorDimension, flavor: Flavor) {
    if (dimension == FlavorDimension.ENVIRONMENT && flavor != Flavor.PRODUCTION) {
        setApplicationIdSuffix(flavor)
        setManifestPlaceholders(flavor)
    }
}

private fun ApplicationProductFlavor.setApplicationIdSuffix(flavor: Flavor) {
    versionNameSuffix = "-$flavor"
    applicationIdSuffix = ".$flavor"
}

private fun ApplicationProductFlavor.setManifestPlaceholders(flavor: Flavor) {
    manifestPlaceholders["appName"] = "${AppConfig.APP_NAME} - ${flavor.name}"
    manifestPlaceholders["usesCleartextTraffic"] = true
}