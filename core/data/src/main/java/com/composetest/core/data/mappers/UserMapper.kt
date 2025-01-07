package com.composetest.core.data.mappers

import com.composetest.core.data.api.responses.AuthenticationResponse
import com.composetest.core.database.entities.UserEntity
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.providers.CipherProvider
import javax.inject.Inject

internal class UserMapper @Inject constructor(
    private val cipherProvider: CipherProvider
) {

    operator fun invoke(model: UserModel) = UserEntity(
        id = model.id,
        name = model.name,
        email = model.email,
        encryptedPassword = model.encryptedPassword
    )

    operator fun invoke(entity: UserEntity?) = entity?.let {
        UserModel(
            id = it.id,
            email = it.email,
            name = it.name,
            encryptedPassword = it.encryptedPassword
        )
    }

    operator fun invoke(
        authenticationResponse: AuthenticationResponse,
        password: String
    ) = UserModel(
        id = authenticationResponse.userId,
        email = authenticationResponse.userEmail,
        encryptedPassword = cipherProvider.encrypt(password),
        name = authenticationResponse.userName,
    )
}