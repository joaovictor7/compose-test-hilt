package modularization

import appconfig.AppBuildType
import appconfig.AppFlavor
import com.android.build.api.dsl.ApplicationBaseFlavor
import com.android.build.api.dsl.ApplicationBuildType

internal fun ApplicationBuildType.setBuildConfigFields(buildType: AppBuildType) {
    buildConfigField("String", "BFF_API_HOST", "\"192.168.1.15\"") // Macbook
//    buildConfigField("String", "BFF_API_HOST", "\"10.0.2.2\"") // Emulator
    buildConfigField("int", "BFF_API_PORT", "8080")
}

internal fun ApplicationBaseFlavor.setBuildConfigFields(flavor: AppFlavor) {}