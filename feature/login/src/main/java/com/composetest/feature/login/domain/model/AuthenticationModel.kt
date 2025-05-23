package com.composetest.feature.login.domain.model

import com.composetest.core.domain.model.UserModel
import java.time.LocalDateTime

internal data class AuthenticationModel(
    val sessionToken: String,
    val sessionStartDateTime: LocalDateTime,
    val user: UserModel
)
