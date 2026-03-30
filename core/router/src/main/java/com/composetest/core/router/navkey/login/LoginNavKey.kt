package com.composetest.core.router.navkey.login

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class LoginNavKey(
    val isLogout: Boolean = false,
    val expiredSession: Boolean = false,
) : NavKey
