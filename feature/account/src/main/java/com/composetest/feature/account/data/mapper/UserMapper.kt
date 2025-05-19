package com.composetest.feature.account.data.mapper

import com.composetest.core.database.entities.UserEntity
import com.composetest.core.domain.models.UserModel
import javax.inject.Inject

internal class UserMapper @Inject constructor() {

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
}