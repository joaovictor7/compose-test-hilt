package com.composetest.feature.account.data.mapper

import com.composetest.core.database.data.entity.UserEntity
import com.composetest.core.domain.model.UserModel
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