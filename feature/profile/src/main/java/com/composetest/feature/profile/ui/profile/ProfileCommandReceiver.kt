package com.composetest.feature.profile.ui.profile

import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.profile.models.ProfileFormModel

internal interface ProfileCommandReceiver : CommandReceiver<ProfileCommandReceiver> {
    fun setFormData(profileFormModel: ProfileFormModel)
    fun saveProfile()
}