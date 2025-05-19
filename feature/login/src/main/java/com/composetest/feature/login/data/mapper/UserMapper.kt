package com.composetest.feature.login.data.mapper

import com.composetest.core.domain.model.UserModel
import com.composetest.feature.login.network.response.AuthenticationResponse
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