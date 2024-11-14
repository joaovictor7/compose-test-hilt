package com.composetest.core.domain.remoteconfigs

import com.composetest.core.domain.interfaces.RemoteConfig

internal sealed interface FeatureRemoteConfig : RemoteConfig {
    data object Home : FeatureRemoteConfig {
        override val key = "feature_home"
    }
    data object Profile : FeatureRemoteConfig {
        override val key = "feature_profile"
    }
    data object Configuration : FeatureRemoteConfig {
        override val key = "feature_configuration"
    }
}