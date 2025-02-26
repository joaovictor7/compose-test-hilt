package com.composetest.feature.exchange.ui.list

import com.composetest.common.extensions.orFalse
import com.composetest.core.designsystem.utils.getCommonSimpleDialogErrorParam
import com.composetest.core.domain.models.ExchangeModel
import com.composetest.core.domain.usecases.GetAllExchangesUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.feature.exchange.analytics.list.ExchangeListScreenAnalytic
import com.composetest.feature.exchange.mappers.ExchangeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ExchangeListViewModel @Inject constructor(
    private val getAllExchangesUseCase: GetAllExchangesUseCase,
    private val exchangeMapper: ExchangeMapper,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
) : BaseViewModel(), UiState<ExchangeListUiState>, UiEvent<ExchangeListUiEvent>,
    ExchangeListCommandReceiver {

    private val _uiState = MutableStateFlow(ExchangeListUiState())
    private val _uiEvent = MutableSharedFlow<ExchangeListUiEvent>()
    private var exchangeList = listOf<ExchangeModel>()

    override val uiState = _uiState.asStateFlow()
    override val uiEvent = _uiEvent.asSharedFlow()
    override val commandReceiver = this
    override val analyticScreen = ExchangeListScreenAnalytic

    init {
        openScreenAnalytic()
        getAllExchanges()
    }

    override fun getAllExchanges() {
        _uiState.update { it.setIsLoading(true) }
        runAsyncTask(
            onError = { errorHandler(it) },
            onCompletion = { _uiState.update { it.setIsLoading(false) } }
        ) {
            exchangeList = getAllExchangesUseCase()
            _uiState.update { it.setExchangeScreenList(exchangeMapper(exchangeList)) }
        }
    }

    override fun navigateToDetail(exchangeId: String) {
        val exchange = exchangeList.find { it.id == exchangeId }
        exchangeMapper(exchange)?.let {
            _uiEvent.emitEvent(ExchangeListUiEvent.NavigateTo(NavigationModel(it)))
        }
    }

    override fun exchangeFilter(exchange: String) {
        val exchangesFiltered = exchangeList.filter {
            it.name?.contains(exchange, true).orFalse
        }
        _uiState.update { it.setExchangeListFiltered(exchange, exchangeMapper(exchangesFiltered)) }
    }

    override fun dismissSimpleDialog() {
        _uiState.update { it.setSimpleDialogParam(null) }
    }

    private fun errorHandler(error: Throwable) {
        _uiState.update { it.setSimpleDialogParam(getCommonSimpleDialogErrorParam(error)) }
    }
}