package com.composetest.core.domain.enums

import com.composetest.common.remoteconfig.RemoteConfig

enum class Feature(internal val remoteConfig: RemoteConfig) {
    HOME(RemoteConfigKey.FEATURE_HOME),
    WEATHER_FORECAST(RemoteConfigKey.FEATURE_WEATHER_FORECAST),
    NEWS(RemoteConfigKey.FEATURE_NEWS),
    PROFILE(RemoteConfigKey.FEATURE_PROFILE),
    CONFIGURATION(RemoteConfigKey.FEATURE_CONFIGURATION)
}