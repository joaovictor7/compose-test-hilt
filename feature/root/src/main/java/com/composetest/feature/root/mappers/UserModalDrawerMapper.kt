package com.composetest.feature.root.mappers

import com.composetest.core.domain.models.UserModel
import com.composetest.feature.root.models.UserModalDrawerModel
import javax.inject.Inject

internal class UserModalDrawerMapper @Inject constructor() {

    operator fun invoke(userModel: UserModel?) = UserModalDrawerModel(
        username = userModel?.name,
        email = userModel?.email.orEmpty()
    )
}