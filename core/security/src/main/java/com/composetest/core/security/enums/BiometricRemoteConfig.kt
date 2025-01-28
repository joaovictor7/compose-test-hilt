package com.composetest.core.security.enums

import com.composetest.common.remoteconfig.RemoteConfig

internal enum class BiometricRemoteConfig(override val key: String) : RemoteConfig {
    USE_BIOMETRIC("use_biometric"),
}