package com.composetest.common.managers

import com.composetest.common.analytics.AnalyticScreen
import kotlinx.coroutines.flow.Flow

interface AsyncTaskManager {
    fun setScreenAnalytic(analyticScreen: AnalyticScreen)

    suspend fun <T> runFlowTask(
        flow: Flow<T>,
        onStart: (suspend () -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onError: (suspend (e: Throwable) -> Unit)? = null,
        onCollect: (param: T) -> Unit
    )

    suspend fun runAsyncTask(
        onError: (suspend (Throwable) -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onAsyncTask: suspend () -> Unit
    )
}