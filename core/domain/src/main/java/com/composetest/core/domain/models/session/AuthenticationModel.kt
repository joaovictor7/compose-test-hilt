package com.composetest.core.domain.models.session

import com.composetest.core.domain.models.UserModel
import java.time.LocalDateTime

data class AuthenticationModel(
    val sessionToken: String,
    val sessionStartDateTime: LocalDateTime,
    val user: UserModel
)
