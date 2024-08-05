package com.composetest.core.domain.mappers

import com.composetest.core.data.data.network.responses.UserResponse
import com.composetest.core.domain.models.UserModel
import javax.inject.Inject

class UserModelMapper @Inject constructor() {

    operator fun invoke(response: UserResponse) = UserModel(
        id = response.id,
        email = response.email,
        name = response.name
    )
}