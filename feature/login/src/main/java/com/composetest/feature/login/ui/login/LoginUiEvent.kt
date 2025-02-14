package com.composetest.feature.login.ui.login

import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.interfaces.BaseUiEvent

internal sealed interface LoginUiEvent : BaseUiEvent {
    data object ShowBiometricPrompt : LoginUiEvent
    data class NavigateTo(val navigationModel: NavigationModel) : LoginUiEvent
}