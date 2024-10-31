package com.composetest.common.models

import com.composetest.common.enums.BuildType
import com.composetest.common.enums.FlavorDimension
import com.composetest.common.enums.Flavor

data class BuildConfigModel(
    val applicationId: String,
    val versionName: String,
    val versionCode: Int,
    val buildType: BuildType,
    val flavorDimension: FlavorDimension,
    val flavor: Flavor,
    val androidSdkVersion: Int,
    val buildConfigFieldsModel: BuildConfigFieldsModel
) {

    val isDebug get() = BuildType.DEBUG == buildType
    val isRelease get() = BuildType.RELEASE == buildType
    val versionNameToView: String
        get() {
            val flavor = if (buildType != BuildType.RELEASE) " ($flavor)" else String()
            return "$versionName - $versionCode$flavor"
        }
}