package com.composetest.core.ui.utils

import android.util.Log
import com.composetest.core.analytic.AnalyticScreen
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.ErrorAnalyticEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AsyncTaskUtils(
    private val analyticSender: AnalyticSender,
    private val analyticScreen: AnalyticScreen
) {

    fun runAsyncTask(
        coroutineScope: CoroutineScope,
        onError: (suspend (Throwable) -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onAsyncTask: suspend () -> Unit
    ) {
        coroutineScope.launch {
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

    fun <T> runFlowTask(
        coroutineScope: CoroutineScope,
        flow: Flow<T>,
        onStart: (suspend () -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onError: (suspend (e: Throwable) -> Unit)? = null,
        onCollect: (param: T) -> Unit
    ) = runAsyncTask(coroutineScope, onError, onCompletion) {
        flow.onStart { onStart?.invoke() }
            .catch { errorHandler(it, onError) }
            .onCompletion { onCompletion?.invoke() }
            .collect(onCollect)
    }

    private suspend fun errorHandler(
        error: Throwable,
        onError: (suspend (Throwable) -> Unit)? = null
    ) {
        Log.e("AsyncTaskError", error.message, error)
        analyticSender.sendErrorEvent(ErrorAnalyticEvent(error, analyticScreen))
        onError?.invoke(error)
    }
}