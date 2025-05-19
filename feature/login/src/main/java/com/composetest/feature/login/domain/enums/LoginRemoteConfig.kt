package com.composetest.feature.login.domain.enums

import com.composetest.common.remoteconfig.RemoteConfig

internal enum class LoginRemoteConfig(override val key: String) : RemoteConfig {
    BY_PASS_LOGIN("bypass_login"),
}