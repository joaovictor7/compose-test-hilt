package modularization

import appconfig.AppFlavor
import appconfig.AppFlavor.Companion.allDimensions
import com.android.build.api.dsl.ApplicationExtension

internal fun ApplicationExtension.setFlavors() {
    flavorDimensions.addAll(allDimensions)
    productFlavors {
        AppFlavor.values().forEach { flavor ->
            create(flavor.flavorName) {
                dimension = flavor.dimension
                isDefault = flavor.isDefault
                setBuildConfigFields(flavor)
            }
        }
    }
}