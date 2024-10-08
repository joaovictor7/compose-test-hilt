package com.composetest.core.ui.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composetest.core.domain.interfaces.analytics.AnalyticScreen
import com.composetest.core.domain.models.analytics.ErrorAnalyticEvent
import com.composetest.core.domain.models.analytics.OpenScreenAnalyticEvent
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.ui.interfaces.BaseUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : BaseUiState>(
    private val analyticScreen: AnalyticScreen,
    uiState: UiState
) : ViewModel() {

    abstract val sendAnalyticsUseCase: SendAnalyticsUseCase

    private val _uiState = MutableStateFlow(uiState)
    val uiState = _uiState.asStateFlow()

    protected fun updateUiState(onNewUiState: (UiState) -> UiState) {
        _uiState.update(onNewUiState)
    }

    protected fun openScreenAnalytic() {
        runAsyncTask {
            sendAnalyticsUseCase(OpenScreenAnalyticEvent(analyticScreen))
        }
    }

    protected fun <T> runFlowTask(
        flow: Flow<T>,
        onStart: (suspend () -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onError: (suspend (e: Throwable) -> Unit)? = null,
        onCollect: suspend (param: T) -> Unit
    ) {
        viewModelScope.launch {
            safeRunTask(onError) {
                flow.onStart { onStart?.invoke() }
                    .onCompletion { onCompletion?.invoke() }
                    .collect(onCollect)
            }
        }
    }

    protected fun runAsyncTask(
        onStart: (suspend () -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onError: (suspend (Throwable) -> Unit)? = null,
        onAsyncTask: suspend CoroutineScope.() -> Unit
    ) {
        with(viewModelScope) {
            launch {
                safeRunTask(onError) {
                    onStart?.invoke()
                    onAsyncTask()
                }
            }.invokeOnCompletion {
                launch { safeRunTask(onError) { onCompletion?.invoke() } }
            }
        }
    }

    private suspend fun safeRunTask(
        onError: (suspend (Throwable) -> Unit)?,
        onTask: suspend () -> Unit
    ) {
        runCatching { onTask() }.onFailure {
            sendAnalyticsUseCase(ErrorAnalyticEvent(it, analyticScreen))
            onError?.invoke(it)
        }
    }
}