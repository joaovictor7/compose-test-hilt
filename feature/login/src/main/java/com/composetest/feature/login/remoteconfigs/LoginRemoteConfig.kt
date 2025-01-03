package com.composetest.feature.login.remoteconfigs

import com.composetest.core.domain.remoteconfigs.RemoteConfig

internal sealed interface LoginRemoteConfig : RemoteConfig {
    data object ByPassLogin : LoginRemoteConfig {
        override val key = "bypass_login"
    }
}