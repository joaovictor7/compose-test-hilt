package com.composetest.core.domain.enums

import com.composetest.common.remoteconfig.RemoteConfig

internal enum class DomainRemoteConfig(override val key: String) : RemoteConfig {
    FEATURE_HOME("feature_home"),
    FEATURE_WEATHER_FORECAST("feature_weather_forecast"),
    FEATURE_NEWS("feature_news"),
    FEATURE_PROFILE("feature_profile"),
    FEATURE_CONFIGURATION("feature_configuration"),
    USE_BIOMETRIC("use_biometric"),
}