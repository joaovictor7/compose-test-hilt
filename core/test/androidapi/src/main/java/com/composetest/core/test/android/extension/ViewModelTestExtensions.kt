package com.composetest.core.test.android.extension

import com.composetest.core.test.kotlin.extension.ViewModelTestScope
import com.composetest.core.test.kotlin.extension.runViewModelTest
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <S, E, VM> TestScope.runViewModelTest(
    viewModel: VM,
    block: suspend ViewModelTestScope<S, E>.() -> Unit,
) where VM : UiState<S>, VM : UiEvent<E> = runViewModelTest(viewModel.uiState, viewModel.uiEvent, block)
