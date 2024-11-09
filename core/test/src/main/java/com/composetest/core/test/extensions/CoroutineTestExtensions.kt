package com.composetest.core.test.extensions

import com.composetest.core.test.interfaces.CoroutinesTest
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest

fun <T> CoroutinesTest.runFlowTest(
    flow: Flow<T>,
    onRunTest: suspend (job: Job, collectedStates: List<T>) -> Unit,
) = runTest(testDispatcher) {
    val collectedStates = mutableListOf<T>()
    val job =
        launch {
            flow.toList(collectedStates)
        }
    onRunTest(job, collectedStates)
}