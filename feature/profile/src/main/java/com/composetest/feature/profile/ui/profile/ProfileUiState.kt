package com.composetest.feature.profile.ui.profile

import com.composetest.feature.profile.models.ProfileScreenModel

internal data class ProfileUiState(
    val profileScreenModels: List<ProfileScreenModel> = emptyList()
) {
    fun setProfileScreenModels(models: List<ProfileScreenModel>) = copy(
        profileScreenModels = models
    )
}