package com.composetest.core.network.responses

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(
    val sessionToken: String,
    val sessionStartDateTime: String,
    val userId: String,
    val userEmail: String,
    val userName: String?,
)
