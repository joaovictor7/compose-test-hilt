package com.composetest.feature.profile.ui.profile

import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.profile.models.ProfileFormModel

internal data class ProfileUiState(
    val profileForm: ProfileFormModel? = null
) : BaseUiState