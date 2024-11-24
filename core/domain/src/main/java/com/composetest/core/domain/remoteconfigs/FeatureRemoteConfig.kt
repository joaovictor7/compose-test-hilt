package com.composetest.core.domain.remoteconfigs

import com.composetest.core.domain.interfaces.RemoteConfig

internal sealed interface FeatureRemoteConfig : RemoteConfig {
    data object Home : FeatureRemoteConfig {
        override val key = "feature_home"
    }
    data object Home2 : FeatureRemoteConfig {
        override val key = "feature_home2"
    }
    data object Profile : FeatureRemoteConfig {
        override val key = "feature_profile"
    }
    data object Configuration : FeatureRemoteConfig {
        override val key = "feature_configuration"
    }
}