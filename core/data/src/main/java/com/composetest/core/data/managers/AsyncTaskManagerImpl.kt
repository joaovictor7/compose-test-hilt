package com.composetest.core.data.managers

import com.composetest.common.analytics.AnalyticScreen
import com.composetest.common.analytics.ErrorAnalyticEvent
import com.composetest.common.managers.AsyncTaskManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

internal class AsyncTaskManagerImpl @Inject constructor(
    private val sendAnalyticsUseCase: SendAnalyticsUseCase
) : AsyncTaskManager {

    private val analyticScreen: AnalyticScreen? = null

    override fun setScreenAnalytic(analyticScreen: AnalyticScreen) {

    }

    override suspend fun <T> runFlowTask(
        flow: Flow<T>,
        onStart: (suspend () -> Unit)?,
        onCompletion: (suspend () -> Unit)?,
        onError: (suspend (e: Throwable) -> Unit)?,
        onCollect: (param: T) -> Unit
    ) {
        runCatching {
            flow.onStart { onStart?.invoke() }
                .catch { errorHandler(it, onError) }
                .onCompletion { onCompletion?.invoke() }
                .collect(onCollect)
        }.onFailure { errorHandler(it) }
    }

    override suspend fun runAsyncTask(
        onError: (suspend (Throwable) -> Unit)?,
        onCompletion: (suspend () -> Unit)?,
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

    private suspend fun errorHandler(
        error: Throwable,
        onError: (suspend (Throwable) -> Unit)? = null
    ) {
        analyticScreen?.let { sendAnalyticsUseCase(ErrorAnalyticEvent(error, it)) }
        onError?.invoke(error)
    }
}