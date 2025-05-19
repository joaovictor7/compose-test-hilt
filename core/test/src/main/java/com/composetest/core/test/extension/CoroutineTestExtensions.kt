package com.composetest.core.test.extension

import com.composetest.core.test.CoroutinesTest
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

fun <T> CoroutinesTest.runFlowTest(
    flow: Flow<T>,
    onRunTest: suspend (onCancelJob: () -> Unit, states: List<T>) -> Unit,
) = runTest(testDispatcher) {
    val collectedFlows = collectFlow(flow)
    onRunTest({ collectedFlows.first.cancel() }, collectedFlows.second)
}

fun <T, Y> CoroutinesTest.runFlowTest(
    firstFlow: Flow<T>,
    secondFlow: Flow<Y>,
    onRunTest: suspend (onCancelJob: () -> Unit, firstStates: List<T>, secondState: List<Y>) -> Unit,
) = runTest(testDispatcher) {
    val firstCollectedFlows = collectFlow(firstFlow)
    val secondCollectedFlows = collectFlow(secondFlow)
    onRunTest({
        firstCollectedFlows.first.cancel()
        secondCollectedFlows.first.cancel()
    }, firstCollectedFlows.second, secondCollectedFlows.second)
}

private fun <T> TestScope.collectFlow(flow: Flow<T>): Pair<Job, List<T>> {
    val collectedStates = mutableListOf<T>()
    val job = launch {
        flow.toList(collectedStates)
    }
    return job to collectedStates
}