package com.composetest.core.data.remoteconfigs

import com.composetest.core.domain.remoteconfigs.RemoteConfig

internal sealed interface RemoteConfigKey : RemoteConfig {
    data object UseBiometric : RemoteConfig {
        override val key = "use_biometric"
    }
}
