package com.composetest.core.ui.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composetest.common.analytics.AnalyticScreen
import com.composetest.common.analytics.CommonAnalyticEvent
import com.composetest.common.analytics.ErrorAnalyticEvent
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.interfaces.BaseUiEvent
import com.composetest.core.ui.interfaces.BaseUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : BaseUiState, UiEvent : BaseUiEvent>(
    initialUiState: UiState
) : ViewModel() {

    protected abstract val navigationManager: NavigationManager
    protected abstract val analyticScreen: AnalyticScreen
    protected abstract val sendAnalyticsUseCase: SendAnalyticsUseCase

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState
        .onStart { initUiState() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), initialUiState)

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    open fun navigateBack() {
        navigationManager.navigateBack()
    }

    protected open fun initUiState() {}

    protected fun updateUiState(onNewUiState: (UiState) -> UiState) {
        _uiState.update(onNewUiState)
    }

    protected fun launchUiEvent(uiEvent: UiEvent) {
        _uiEvent.trySend(uiEvent)
    }

    protected fun openScreenAnalytic() {
        runAsyncTask {
            sendAnalyticsUseCase(CommonAnalyticEvent.OpenScreen(analyticScreen))
        }
    }

    protected fun <T> runFlowTask(
        flow: Flow<T>,
        onStart: (suspend () -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onError: (suspend (e: Throwable) -> Unit)? = null,
        onCollect: (param: T) -> Unit
    ) {
        runAsyncTask(onError, onCompletion) {
            flow.onStart { onStart?.invoke() }
                .catch { errorHandler(it, onError) }
                .onCompletion { onCompletion?.invoke() }
                .collect(onCollect)
        }
    }

    protected fun runAsyncTask(
        onError: (suspend (Throwable) -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onAsyncTask: suspend () -> Unit
    ) {
        viewModelScope.launch {
            runCatching {
                try {
                    onAsyncTask()
                } catch (e: Throwable) {
                    errorHandler(e, onError)
                } finally {
                    onCompletion?.invoke()
                }
            }.onFailure { errorHandler(it) }
        }
    }

    private suspend fun errorHandler(
        error: Throwable,
        onError: (suspend (Throwable) -> Unit)? = null
    ) {
        sendAnalyticsUseCase(ErrorAnalyticEvent(error, analyticScreen))
        onError?.invoke(error)
    }
}