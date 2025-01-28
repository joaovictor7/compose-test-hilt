package com.composetest.core.domain.enums.remoteconfigs

import com.composetest.common.remoteconfig.RemoteConfig

internal enum class FeatureRemoteConfig(override val key: String) : RemoteConfig {
    HOME("feature_home"),
    WEATHER_FORECAST("feature_weather_forecast"),
    NEWS("feature_news"),
    PROFILE("feature_profile"),
    CONFIGURATION("feature_configuration"),
}