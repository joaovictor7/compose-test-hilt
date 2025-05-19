package com.composetest.core.domain.model.session

import com.composetest.core.domain.model.UserModel
import java.time.LocalDateTime

data class AuthenticationModel(
    val sessionToken: String,
    val sessionStartDateTime: LocalDateTime,
    val user: UserModel
)
