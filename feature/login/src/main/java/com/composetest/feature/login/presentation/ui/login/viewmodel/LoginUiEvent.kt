package com.composetest.feature.login.presentation.ui.login.viewmodel

import com.composetest.core.router.model.NavigationModel

internal sealed interface LoginUiEvent {
    data object ShowBiometricPrompt : LoginUiEvent
    data class NavigateTo(val navigation: NavigationModel) : LoginUiEvent
}