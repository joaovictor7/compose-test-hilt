package com.composetest.core.data.mappers

import com.composetest.core.database.entities.UserEntity
import com.composetest.core.domain.models.UserModel
import com.composetest.core.network.responses.AuthenticationResponse
import javax.inject.Inject

internal class UserMapper @Inject constructor(
    private val cipherProvider: com.composetest.core.security.providers.CipherProvider
) {

    fun mapperToEntity(model: UserModel) = UserEntity(
        id = model.id,
        name = model.name,
        email = model.email,
        encryptedPassword = model.encryptedPassword
    )

    fun mapperToModel(entity: UserEntity?) = entity?.let {
        UserModel(
            id = it.id,
            email = it.email,
            name = it.name,
            encryptedPassword = it.encryptedPassword
        )
    }

    fun mapperToModel(
        authenticationResponse: AuthenticationResponse,
        password: String
    ) = UserModel(
        id = authenticationResponse.userId,
        email = authenticationResponse.userEmail,
        encryptedPassword = cipherProvider.encrypt(password),
        name = authenticationResponse.userName,
    )
}