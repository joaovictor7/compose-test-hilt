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
) {
    val isRelease get() = buildType == BuildType.RELEASE
    val isProduction get() = flavor == Flavor.PRODUCTION
    val fullyVersion get() = versionName + versionCode
}