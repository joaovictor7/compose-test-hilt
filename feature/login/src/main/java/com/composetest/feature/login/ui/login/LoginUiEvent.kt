package com.composetest.feature.login.ui.login

import com.composetest.core.ui.interfaces.BaseUiEvent

internal sealed interface LoginUiEvent : BaseUiEvent {
    data object Teste : LoginUiEvent
}