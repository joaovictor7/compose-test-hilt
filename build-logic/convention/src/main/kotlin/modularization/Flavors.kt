package modularization

import appconfig.AppConfig
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryExtension
import enums.Flavor
import enums.FlavorDimension
import org.gradle.api.Project

internal fun Project.setAppFlavors(appExtension: ApplicationExtension) {
    with(appExtension) {
        flavorDimensions += FlavorDimension.allDimensions
        productFlavors {
            FlavorDimension.entries.forEach { dimension ->
                dimension.flavors.forEach { flavor ->
                    create(flavor.toString()) {
                        this.dimension = dimension.toString()
                        this.isDefault = flavor.isDefault
                        setNonProductionFields(dimension, flavor)
                        setBuildConfigFields(this@setAppFlavors, flavor)
                    }
                }
            }
        }
    }
}

internal fun Project.setLibraryFlavors(libraryExtension: LibraryExtension) {
    with(libraryExtension) {
        flavorDimensions += FlavorDimension.allDimensions
        productFlavors {
            FlavorDimension.entries.forEach { dimension ->
                dimension.flavors.forEach { flavor ->
                    create(flavor.toString()) {
                        this.dimension = dimension.toString()
                        this.isDefault = flavor.isDefault
                    }
                }
            }
        }
    }
}

private fun ApplicationProductFlavor.setNonProductionFields(
    dimension: FlavorDimension,
    flavor: Flavor
) {
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
