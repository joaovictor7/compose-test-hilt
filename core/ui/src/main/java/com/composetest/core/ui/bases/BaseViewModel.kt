package com.composetest.core.ui.bases

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composetest.common.analytics.AnalyticScreen
import com.composetest.common.analytics.CommonAnalyticEvent
import com.composetest.common.analytics.ErrorAnalyticEvent
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected abstract val analyticScreen: AnalyticScreen
    protected abstract val sendAnalyticsUseCase: SendAnalyticsUseCase

    protected fun openScreenAnalytic() = runAsyncTask {
        sendAnalyticsUseCase(CommonAnalyticEvent.OpenScreen(analyticScreen))
    }

    protected fun <UiEvent> MutableSharedFlow<UiEvent>.emitEvent(uiEvent: UiEvent) =
        runAsyncTask { emit(uiEvent) }

    protected fun runAsyncTask(
        onError: (suspend (Throwable) -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onAsyncTask: suspend () -> Unit
    ) = viewModelScope.launch {
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

    protected fun <T> runFlowTask(
        flow: Flow<T>,
        onStart: (suspend () -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onError: (suspend (e: Throwable) -> Unit)? = null,
        onCollect: (param: T) -> Unit
    ) = runAsyncTask(onError, onCompletion) {
        flow.onStart { onStart?.invoke() }
            .catch { errorHandler(it, onError) }
            .onCompletion { onCompletion?.invoke() }
            .collect(onCollect)
    }

    private suspend fun errorHandler(
        error: Throwable,
        onError: (suspend (Throwable) -> Unit)? = null
    ) {
        Log.e("BaseViewModel", error.message, error)
        sendAnalyticsUseCase(ErrorAnalyticEvent(error, analyticScreen))
        onError?.invoke(error)
    }
}