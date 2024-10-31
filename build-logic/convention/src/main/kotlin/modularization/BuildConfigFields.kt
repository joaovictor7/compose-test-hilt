package modularization

import appconfig.AppBuildType
import appconfig.AppFlavor
import appconfig.AppFlavorDimension
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationProductFlavor

internal fun ApplicationBuildType.setBuildConfigFields(buildType: AppBuildType) {}

internal fun ApplicationProductFlavor.setBuildConfigFields(
    dimension: AppFlavorDimension,
    flavor: AppFlavor
) {
    buildConfigField("String", "BFF_API_HOST", "\"\"") // Macbook
//    buildConfigField("String", "BFF_API_HOST", "\"10.0.2.2\"") // Emulator
    buildConfigField("int", "BFF_API_PORT", "0")
}