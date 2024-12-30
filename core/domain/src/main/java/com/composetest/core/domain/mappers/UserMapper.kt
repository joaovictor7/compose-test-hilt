package com.composetest.core.domain.mappers

import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.AuthenticationModel
import com.composetest.core.domain.providers.CipherProvider
import javax.inject.Inject

internal class UserMapper @Inject constructor(private val cipherProvider: CipherProvider) {

    operator fun invoke(
        authenticationModel: AuthenticationModel,
        email: String,
        password: String
    ) = UserModel(
        id = authenticationModel.userId,
        email = email,
        encryptedPassword = cipherProvider.encrypt(password),
        name = authenticationModel.userName,
    )
}