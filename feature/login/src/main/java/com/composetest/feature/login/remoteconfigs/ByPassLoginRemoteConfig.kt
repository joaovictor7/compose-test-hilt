package com.composetest.feature.login.remoteconfigs

import com.composetest.core.domain.interfaces.RemoteConfig

object ByPassLoginRemoteConfig : RemoteConfig {
    override val key = "bypass_login"
}