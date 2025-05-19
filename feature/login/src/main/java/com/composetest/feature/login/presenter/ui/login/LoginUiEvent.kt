package com.composetest.feature.login.presenter.ui.login

import com.composetest.core.router.model.NavigationModel

internal sealed interface LoginUiEvent {
    data object ShowBiometricPrompt : LoginUiEvent
    data class NavigateTo(val navigationModel: NavigationModel) : LoginUiEvent
}