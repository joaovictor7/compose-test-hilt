package com.composetest.core.router.destination.login

import com.composetest.core.router.interfaces.Destination
import kotlinx.serialization.Serializable

@Serializable
data class LoginDestination(
    val isLogout: Boolean = false,
    val expiredSession: Boolean = false,
) : Destination
