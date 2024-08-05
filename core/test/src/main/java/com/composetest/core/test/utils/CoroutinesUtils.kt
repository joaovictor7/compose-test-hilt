package com.composetest.core.test.utils

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest

fun <T> runStateFlowTest(
    testDispatcher: TestDispatcher,
    stateFlow: StateFlow<T>,
    onRunTest: (job: Job, collectedUiStates: List<T>) -> Unit
) = runTest(testDispatcher) {
    val collectedStates = mutableListOf<T>()
    val job = launch {
        stateFlow.toList(collectedStates)
    }
    onRunTest(job, collectedStates)
}