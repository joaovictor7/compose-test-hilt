package com.composetest.core.data.api.responses

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(
    val user: UserResponse,
    val sessionResponse: SessionResponse
)
