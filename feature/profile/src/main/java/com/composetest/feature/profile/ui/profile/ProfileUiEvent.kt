package com.composetest.feature.profile.ui.profile

import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.interfaces.BaseUiEvent

internal sealed interface ProfileUiEvent : BaseUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : ProfileUiEvent
}