package com.composetest.feature.exchange.presenter.ui.list

import androidx.lifecycle.viewModelScope
import com.composetest.common.extension.orFalse
import com.composetest.core.analytic.event.CommonAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.router.extension.dialogErrorDestination
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.interfaces.ViewModelParamFactory
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.exchange.analytic.screen.ExchangeListScreenAnalytic
import com.composetest.feature.exchange.domain.model.ExchangeModel
import com.composetest.feature.exchange.domain.usecase.GetAllExchangesUseCase
import com.composetest.feature.exchange.navigation.param.ExchangeListDeepLinkParam
import com.composetest.feature.exchange.presenter.mapper.ExchangeMapper
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = ExchangeListViewModel.Factory::class)
internal class ExchangeListViewModel @AssistedInject constructor(
    private val getAllExchangesUseCase: GetAllExchangesUseCase,
    private val exchangeMapper: ExchangeMapper,
    private val analyticSender: AnalyticSender,
    @Assisted private val deepLinkParam: ExchangeListDeepLinkParam?,
    @param:AsyncTaskUtilsQualifier(ExchangeListScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(),
    UiState<ExchangeListUiState>,
    UiEvent<ExchangeListUiEvent>,
    ExchangeListIntentReceiver {

    override val intentReceiver = this

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
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ExchangeListScreenAnalytic))
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
            deepLinkParam?.let { exchangeFilter(it.filter) }
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
        _uiEvent.emitEvent(ExchangeListUiEvent.NavigateTo(error.dialogErrorDestination()))
    }

    @AssistedFactory
    interface Factory : ViewModelParamFactory<ExchangeListDeepLinkParam, ExchangeListViewModel>
}