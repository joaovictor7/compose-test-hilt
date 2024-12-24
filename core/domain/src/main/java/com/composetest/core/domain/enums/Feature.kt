package com.composetest.core.domain.enums

import com.composetest.core.domain.remoteconfigs.RemoteConfigKey

enum class Feature(internal val remoteConfig: RemoteConfigKey) {
    HOME(RemoteConfigKey.FeatureHome),
    HOME2(RemoteConfigKey.FeatureHome2),
    PROFILE(RemoteConfigKey.FeatureProfile),
    CONFIGURATION(RemoteConfigKey.FeatureConfiguration)
}