package com.composetest.core.data.api.responses

import java.time.LocalDateTime

internal data class AuthenticationResponse(
    val sessionToken: String,
    val sessionStartDateTime: LocalDateTime,
    val userId: String,
    val userEmail: String,
    val userName: String?,
)
