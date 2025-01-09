package com.composetest.core.ui.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composetest.core.domain.analytics.AnalyticScreen
import com.composetest.core.domain.analytics.CommonAnalyticEvent
import com.composetest.core.domain.analytics.ErrorAnalyticEvent
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.interfaces.BaseUiEvent
import com.composetest.core.ui.interfaces.BaseUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : BaseUiState, UiEvent : BaseUiEvent>(
    private val analyticScreen: AnalyticScreen,
    uiState: UiState
) : ViewModel() {

    abstract val sendAnalyticsUseCase: SendAnalyticsUseCase
    abstract val navigationManager: NavigationManager

    private val _uiState = MutableStateFlow(uiState)
    val uiState = _uiState
        .onStart { initUiState() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), uiState)

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    abstract fun initUiState()

    open fun navigateBack() {
        navigationManager.navigateBack()
    }

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
        viewModelScope.launch {
            runCatching {
                flow.onStart { onStart?.invoke() }
                    .catch { errorHandle(it, onError) }
                    .onCompletion { onCompletion?.invoke() }
                    .collect(onCollect)
            }.onFailure { errorHandle(it) }
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
                    errorHandle(e, onError)
                } finally {
                    onCompletion?.invoke()
                }
            }.onFailure { errorHandle(it) }
        }
    }

    private suspend fun errorHandle(
        error: Throwable,
        onError: (suspend (Throwable) -> Unit)? = null
    ) {
        sendAnalyticsUseCase(ErrorAnalyticEvent(error, analyticScreen))
        onError?.invoke(error)
    }
}