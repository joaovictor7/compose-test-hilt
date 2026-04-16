package com.composetest.core.test.kotlin.extension

import com.composetest.core.test.kotlin.CoroutinesTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals


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

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <S, E> TestScope.runViewModelTest(
    uiState: StateFlow<S>,
    uiEvent: SharedFlow<E>,
    block: suspend ViewModelTestScope<S, E>.() -> Unit,
) = ViewModelTestScope(uiState, uiEvent, this).block()

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTestScope<S, E> internal constructor(
    private val uiState: StateFlow<S>,
    private val uiEvent: SharedFlow<E>,
    private val testScope: TestScope,
) {
    private var eventIndex = 0

    fun advanceUntilIdle() = testScope.advanceUntilIdle()

    fun uiStateCheck(expected: S) {
        assertEquals(expected, uiState.value)
    }

    fun uiStateCheck(assertion: (S) -> Unit) {
        assertion(uiState.value)
    }

    fun uiEventCheck(expected: E) {
        assertEquals(expected, uiEvent.replayCache.getOrNull(eventIndex++))
    }
}