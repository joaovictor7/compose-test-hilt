package com.composetest.core.domain.models.session

import java.time.LocalDateTime

data class AuthenticationModel(
    val sessionToken: String,
    val sessionStartDateTime: LocalDateTime,
    val userId: String,
    val userEmail: String,
    val userName: String?,
)
