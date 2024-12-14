package com.composetest.feature.login.remoteconfigs

import com.composetest.core.domain.remoteconfigs.RemoteConfig

internal sealed interface LoginRemoteConfig : RemoteConfig {
    data object BypassLogin: LoginRemoteConfig {
        override val key = "bypass_login"
    }
}