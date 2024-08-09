package com.composetest.core.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val name: String? = null,
    val email: String
)
