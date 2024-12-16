package com.composetest.core.data.api.responses

import kotlinx.serialization.Serializable

@Serializable
data class SessionResponse(
    val token: String,
    val startDate: String,
    val endDate: String
)
