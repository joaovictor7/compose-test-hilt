package com.composetest.core.domain.enums

import com.composetest.common.remoteconfig.RemoteConfig
import com.composetest.core.domain.enums.remoteconfigs.FeatureRemoteConfig

enum class Feature(internal val remoteConfig: RemoteConfig) {
    HOME(FeatureRemoteConfig.HOME),
    WEATHER_FORECAST(FeatureRemoteConfig.WEATHER_FORECAST),
    NEWS(FeatureRemoteConfig.NEWS),
    PROFILE(FeatureRemoteConfig.PROFILE),
    CONFIGURATION(FeatureRemoteConfig.CONFIGURATION),
    EXCHANGE(FeatureRemoteConfig.EXCHANGE),
}