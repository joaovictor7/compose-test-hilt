package com.composetest.feature.login.data.mappers

import com.composetest.core.domain.models.UserModel
import com.composetest.feature.login.network.responses.AuthenticationResponse
import javax.inject.Inject

internal class UserMapper @Inject constructor() {

    fun mapperToModel(
        authenticationResponse: AuthenticationResponse,
        encryptedPassword: String
    ) = UserModel(
        id = authenticationResponse.userId,
        email = authenticationResponse.userEmail,
        encryptedPassword = encryptedPassword,
        name = authenticationResponse.userName,
    )
}