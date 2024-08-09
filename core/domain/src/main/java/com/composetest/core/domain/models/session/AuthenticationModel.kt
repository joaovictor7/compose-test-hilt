package com.composetest.core.domain.models.session

import com.composetest.core.domain.models.UserModel

data class AuthenticationModel(
    val session: SessionModel,
    val user: UserModel
)
