package com.composetest.core.domain.enums

import com.composetest.core.domain.remoteconfigs.FeatureRemoteConfig

enum class Feature(internal val remoteConfig: FeatureRemoteConfig) {
    HOME(FeatureRemoteConfig.Home),
    PROFILE(FeatureRemoteConfig.Profile),
    CONFIGURATION(FeatureRemoteConfig.Configuration)
}