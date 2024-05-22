package com.composetest.core.designsystem.domain.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event, State : BaseState>(stateInstance: State) : ViewModel() {

    private val _state = MutableStateFlow(stateInstance)
    val state = _state.asStateFlow()

    protected val stateValue: State get() = state.value

    abstract fun handleEvent(event: Event)

    protected fun updateState(newState: (State) -> State) {
        _state.update(newState)
    }

    protected fun <T> lazyFlowTask(
        showLoading: Boolean = false,
        flowTask: Flow<T>,
        onError: (e: Throwable) -> Unit = ::handleError,
        onSuccess: (param: T) -> Unit,
    ) {
        viewModelScope.launch {
            flowTask.onStart {
                //showLoading(showLoading)
            }.onCompletion {
                //showLoading(false)
            }.catch {
                onError.invoke(it)
            }.collect {
                onSuccess.invoke(it)
            }
        }
    }

    protected open fun handleError(e: Throwable) {

    }
}