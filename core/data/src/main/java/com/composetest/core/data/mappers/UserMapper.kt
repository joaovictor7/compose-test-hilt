package com.composetest.core.data.mappers

import com.composetest.core.database.entities.UserEntity
import com.composetest.core.domain.models.UserModel
import javax.inject.Inject

internal class UserMapper @Inject constructor() {

    operator fun invoke(model: UserModel) = UserEntity(
        id = model.id,
        name = model.name,
        email = model.email
    )

    operator fun invoke(entity: UserEntity?) = entity?.let {
        UserModel(
            id = it.id,
            email = it.email,
            name = it.name
        )
    }
}