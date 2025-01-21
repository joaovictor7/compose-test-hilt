package com.composetest.core.domain.enums

import com.composetest.common.remoteconfig.RemoteConfig

enum class Feature(internal val remoteConfig: RemoteConfig) {
    HOME(DomainRemoteConfig.FEATURE_HOME),
    WEATHER_FORECAST(DomainRemoteConfig.FEATURE_WEATHER_FORECAST),
    NEWS(DomainRemoteConfig.FEATURE_NEWS),
    PROFILE(DomainRemoteConfig.FEATURE_PROFILE),
    CONFIGURATION(DomainRemoteConfig.FEATURE_CONFIGURATION)
}