package com.composetest.feature.profile.ui.editprofile

import com.composetest.feature.profile.models.ProfileFormModel

internal data class EditProfileUiState(
    val saveButtonEnabled: Boolean = false,
    val profileForm: ProfileFormModel? = null
)