package com.composetest.core.domain.enums

import com.composetest.core.domain.remoteconfigs.FeatureRemoteConfig

enum class Feature(internal val remoteConfig: FeatureRemoteConfig) {
    HOME(FeatureRemoteConfig.Home),
    HOME2(FeatureRemoteConfig.Home2),
    PROFILE(FeatureRemoteConfig.Profile),
    CONFIGURATION(FeatureRemoteConfig.Configuration)
}