package com.composetest.core.ui.util

import android.util.Log
import com.composetest.core.analytic.api.event.ErrorAnalyticEvent
import com.composetest.core.analytic.api.screen.ScreenAnalytic
import com.composetest.core.analytic.api.sender.AnalyticSender
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class AsyncTaskUtils(
    private val analyticSender: AnalyticSender,
    private val screenAnalytic: ScreenAnalytic,
) {

    suspend fun runAsyncTask(
        onError: (suspend (Throwable) -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onAsyncTask: suspend () -> Unit
    ) {
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

    suspend fun <T> runFlowTask(
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
        Log.e("AsyncTaskError", error.message, error)
        analyticSender.sendErrorEvent(ErrorAnalyticEvent(error, screenAnalytic))
        onError?.invoke(error)
    }
}