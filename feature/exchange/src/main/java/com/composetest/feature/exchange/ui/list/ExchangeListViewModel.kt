package com.composetest.feature.exchange.ui.list

import androidx.lifecycle.viewModelScope
import com.composetest.common.extensions.orFalse
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.domain.models.exchange.ExchangeModel
import com.composetest.core.domain.usecases.exchange.GetAllExchangesUseCase
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.router.utils.getDialogErrorDestination
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.utils.AsyncTaskUtils
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
    private val analyticSender: AnalyticSender,
    @AsyncTaskUtilsQualifier(ScreensAnalytic.EXCHANGE_LIST) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<ExchangeListUiState>, UiEvent<ExchangeListUiEvent>,
    ExchangeListCommandReceiver {

    override val commandReceiver = this

    private var exchangeList = emptyList<ExchangeModel>()

    private val _uiState = MutableStateFlow(ExchangeListUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ExchangeListUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        sendOpenScreenAnalytic()
        getAllExchanges()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ScreensAnalytic.EXCHANGE_LIST))
        }
    }

    override fun getAllExchanges() {
        _uiState.update { it.setIsLoading(true) }
        asyncTaskUtils.runAsyncTask(
            coroutineScope = viewModelScope,
            onError = { errorHandler(it) },
            onCompletion = { _uiState.update { it.setIsLoading(false) } }
        ) {
            exchangeList = getAllExchangesUseCase()
            _uiState.update { it.setExchangeScreenList(exchangeMapper.mapperToModels(exchangeList)) }
        }
    }

    override fun navigateToDetail(exchangeId: String) {
        val exchange = exchangeList.find { it.id == exchangeId }
        exchangeMapper.mapperToDestination(exchange)?.let {
            _uiEvent.emitEvent(ExchangeListUiEvent.NavigateTo(NavigationModel(it)))
        }
    }

    override fun exchangeFilter(exchange: String) {
        val exchangesFiltered = exchangeList.filter {
            it.name?.contains(exchange, true).orFalse
        }
        _uiState.update {
            it.setExchangeListFiltered(
                exchange,
                exchangeMapper.mapperToModels(exchangesFiltered)
            )
        }
    }

    override fun dismissSimpleDialog() {
        _uiState.update { it.setSimpleDialogParam(null) }
    }

    private fun errorHandler(error: Throwable) {
        _uiEvent.emitEvent(ExchangeListUiEvent.NavigateTo(getDialogErrorDestination(error)))
    }
}