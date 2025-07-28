package com.composetest.core.test.kotlin.extension

import com.composetest.core.test.kotlin.CoroutinesTest
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

fun <T> CoroutinesTest.runFlowTest(
    flow: Flow<T>,
    onSetup: (suspend () -> Unit)? = null,
    onTrigger: (suspend () -> Unit)? = null,
    onVerify: suspend (states: List<T>) -> Unit,
) = runTest(testDispatcher) {
    val collectedFlows = collectFlow(flow)
    onSetup?.invoke()
    onTrigger?.invoke()
    collectedFlows.first.cancel()
    onVerify(collectedFlows.second)
}

fun <T, Y> CoroutinesTest.runFlowTest(
    firstFlow: Flow<T>,
    secondFlow: Flow<Y>,
    onSetup: (suspend () -> Unit)? = null,
    onTrigger: (suspend () -> Unit)? = null,
    onVerify: suspend (states: List<T>, secondStates: List<Y>) -> Unit,
) = runTest(testDispatcher) {
    val firstCollectedFlows = collectFlow(firstFlow)
    val secondCollectedFlows = collectFlow(secondFlow)
    onSetup?.invoke()
    onTrigger?.invoke()
    firstCollectedFlows.first.cancel()
    secondCollectedFlows.first.cancel()
    onVerify(firstCollectedFlows.second, secondCollectedFlows.second)
}

private fun <T> TestScope.collectFlow(flow: Flow<T>): Pair<Job, List<T>> {
    val collectedStates = mutableListOf<T>()
    val job = launch {
        flow.toList(collectedStates)
    }
    return job to collectedStates
}