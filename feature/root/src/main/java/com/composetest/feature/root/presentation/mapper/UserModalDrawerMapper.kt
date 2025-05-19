package com.composetest.feature.root.presentation.mapper

import com.composetest.core.domain.model.UserModel
import com.composetest.feature.root.presentation.model.UserModalDrawerModel
import javax.inject.Inject

internal class UserModalDrawerMapper @Inject constructor() {

    fun mapperToModel(userModel: UserModel?) = UserModalDrawerModel(
        username = userModel?.name,
        email = userModel?.email.orEmpty()
    )
}