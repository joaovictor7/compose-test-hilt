package com.composetest.feature.login.domain.enums

import com.composetest.core.domain.interfaces.RemoteConfig

internal enum class LoginRemoteConfig(override val key: String) : RemoteConfig {
    BY_PASS_LOGIN("bypass_login"),
}