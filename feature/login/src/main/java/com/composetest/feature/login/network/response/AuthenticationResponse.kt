package com.composetest.feature.login.network.response

import kotlinx.serialization.Serializable

@Serializable
internal data class AuthenticationResponse(
    val sessionToken: String,
    val sessionStartDateTime: String,
    val userId: String,
    val userEmail: String,
    val userName: String?,
)