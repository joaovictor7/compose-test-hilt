package com.composetest.core.security.impl.enums

import com.composetest.core.domain.interfaces.RemoteConfig

internal enum class BiometricRemoteConfig(override val key: String) : RemoteConfig {
    USE_BIOMETRIC("use_biometric"),
}