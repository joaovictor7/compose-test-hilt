package com.composetest.feature.profile.mappers

import com.composetest.core.domain.models.UserModel
import com.composetest.feature.profile.models.ProfileFormModel
import javax.inject.Inject

internal class ProfileFormMapper @Inject constructor() {
    fun mapperToModel(userModel: UserModel?) = userModel?.let {
        ProfileFormModel(
            email = it.email,
            name = it.name
        )
    }
}