package com.composetest.common.models

import com.composetest.common.enums.BuildType
import com.composetest.common.enums.Flavor
import com.composetest.common.enums.FlavorDimension

data class BuildConfigModel(
    val applicationId: String,
    val versionName: String,
    val versionCode: Int,
    val buildType: BuildType,
    val flavorDimension: FlavorDimension,
    val flavor: Flavor,
    val androidSdkVersion: Int,
    val buildConfigFieldsModel: BuildConfigFieldsModel,
) {
    val isRelease get() = buildType == BuildType.RELEASE
    val isProduction get() = flavorDimension == FlavorDimension.PRODUCTION
    val fullyVersion get() = versionName + versionCode
}