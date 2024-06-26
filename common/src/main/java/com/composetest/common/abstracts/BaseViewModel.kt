package com.composetest.common.abstracts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState>(uiState: UiState) : ViewModel() {

    abstract val dispatcher: CoroutineDispatcher

    private val _uiState = MutableStateFlow(uiState)
    val uiState = _uiState.asStateFlow()

    protected fun updateUiState(onNewUiState: (UiState) -> UiState) {
        _uiState.update(onNewUiState)
    }

    protected fun <T> safeRunFlowTask(
        flowTask: Flow<T>,
        onError: ((e: Throwable) -> Unit)? = null,
        onStart: (() -> Unit)? = null,
        onCompletion: (() -> Unit)? = null,
        onCollect: (param: T) -> Unit
    ) {
        viewModelScope.launch {
            flowTask
                .flowOn(dispatcher)
                .onStart { onStart?.invoke() }
                .onCompletion { onCompletion?.invoke() }
                .catch { onError?.invoke(it) }
                .collect { onCollect.invoke(it) }
        }
    }
}