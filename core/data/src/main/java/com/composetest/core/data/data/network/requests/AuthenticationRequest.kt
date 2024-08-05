package com.composetest.core.data.data.network.requests

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationRequest(
    val email: String,
    val password: String
)
