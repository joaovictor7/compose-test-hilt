package com.composetest.core.data.api.requests

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationRequest(
    val email: String,
    val password: String
)
