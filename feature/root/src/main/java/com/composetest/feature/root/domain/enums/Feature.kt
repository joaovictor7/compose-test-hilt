package com.composetest.feature.root.domain.enums

import com.composetest.common.remoteconfig.RemoteConfig

enum class Feature(internal val remoteConfig: RemoteConfig) {
    HOME(FeatureRemoteConfig.HOME),
    WEATHER_FORECAST(FeatureRemoteConfig.WEATHER_FORECAST),
    NEWS(FeatureRemoteConfig.NEWS),
    PROFILE(FeatureRemoteConfig.PROFILE),
    CONFIGURATION(FeatureRemoteConfig.CONFIGURATION),
    EXCHANGE(FeatureRemoteConfig.EXCHANGE),
}