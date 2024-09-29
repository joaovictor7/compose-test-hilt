package com.composetest.feature.profile.ui.editprofile

import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.profile.models.ProfileFormModel

internal interface EditProfileCommandReceiver : CommandReceiver<EditProfileCommandReceiver> {
    fun setFormData(profileFormModel: ProfileFormModel)
    fun saveProfile()
}