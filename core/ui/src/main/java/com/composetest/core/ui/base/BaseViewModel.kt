package com.composetest.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <UiEvent> MutableSharedFlow<UiEvent>.emitEvent(uiEvent: UiEvent) =
        viewModelScope.launch { emit(uiEvent) }

    open fun sendOpenScreenAnalytic() {}
}