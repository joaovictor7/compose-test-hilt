package com.composetest.common.models

import com.composetest.common.enums.BuildType
import com.composetest.common.enums.Flavor

data class BuildConfigModel(
    val applicationId: String,
    val versionName: String,
    val versionCode: Int,
    val buildType: BuildType,
    val flavor: Flavor,
    val androidSdkVersion: Int,
    val buildConfigFieldsModel: BuildConfigFieldsModel
) {

    val isDebug = BuildType.DEBUG == buildType
    val isStaging = BuildType.STAGING == buildType
    val isRelease = BuildType.RELEASE == buildType

    val versionNameForView: String
        get() {
            val flavor = if (buildType != BuildType.RELEASE) " (${flavor.flavorName})" else String()
            return "$versionName - $versionCode$flavor"
        }
}