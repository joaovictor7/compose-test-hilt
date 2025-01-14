package com.composetest.core.domain.remoteconfigs

internal sealed interface RemoteConfigKey : RemoteConfig {
    data object FeatureHome : RemoteConfigKey {
        override val key = "feature_home"
    }

    data object FeatureWeatherForecast : RemoteConfigKey {
        override val key = "feature_weather_forecast"
    }

    data object FeatureNews : RemoteConfigKey {
        override val key = "feature_news"
    }

    data object FeatureProfile : RemoteConfigKey {
        override val key = "feature_profile"
    }

    data object FeatureConfiguration : RemoteConfigKey {
        override val key = "feature_configuration"
    }

    data object UseBiometric : RemoteConfig {
        override val key = "use_biometric"
    }
}