package com.composetest.core.domain.models.session

import com.composetest.core.domain.models.UserModel

data class AuthenticationModel(
    val sessionToken: String,
    val user: UserModel
)
