package com.composetest.feature.profile.ui.profile

import com.composetest.core.router.models.NavigationModel

internal sealed interface ProfileUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : ProfileUiEvent
}