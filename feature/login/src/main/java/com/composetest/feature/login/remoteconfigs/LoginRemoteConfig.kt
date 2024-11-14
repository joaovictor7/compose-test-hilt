package com.composetest.feature.login.remoteconfigs

import com.composetest.core.domain.interfaces.RemoteConfig

internal sealed interface LoginRemoteConfig : RemoteConfig {
    data object BypassLogin: LoginRemoteConfig {
        override val key = "bypass_login"
    }
}