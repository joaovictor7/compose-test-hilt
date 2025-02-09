package com.composetest.feature.exchange.ui.list

import com.composetest.common.extensions.orFalse
import com.composetest.core.designsystem.utils.getCommonSimpleDialogErrorParam
import com.composetest.core.domain.models.ExchangeModel
import com.composetest.core.domain.usecases.GetAllExchangesUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.exchange.analytics.list.ExchangeListScreenAnalytic
import com.composetest.feature.exchange.mappers.ExchangeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ExchangeListViewModel @Inject constructor(
    private val getAllExchangesUseCase: GetAllExchangesUseCase,
    private val exchangeMapper: ExchangeMapper,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<ExchangeListUiState, ExchangeListUiEvent>(
    ExchangeListUiState()
), ExchangeListCommandReceiver {

    private var exchangeList = listOf<ExchangeModel>()

    override val commandReceiver = this
    override val analyticScreen = ExchangeListScreenAnalytic

    init {
        openScreenAnalytic()
        getAllExchanges()
    }

    override fun getAllExchanges() {
        updateUiState { it.setIsLoading(true) }
        runAsyncTask(
            onError = { errorHandler(it) },
            onCompletion = { updateUiState { it.setIsLoading(false) } }
        ) {
            exchangeList = getAllExchangesUseCase()
            updateUiState { it.setExchangeScreenList(exchangeMapper(exchangeList)) }
        }
    }

    override fun navigateToDetail(exchangeId: String) {
        val exchange = exchangeList.find { it.id == exchangeId }
        exchangeMapper(exchange)?.let {
            navigationManager.navigate(it)
        }
    }

    override fun exchangeFilter(exchange: String) {
        val exchangesFiltered = exchangeList.filter {
            it.name?.contains(exchange, true).orFalse
        }
        updateUiState { it.setExchangeListFiltered(exchange, exchangeMapper(exchangesFiltered)) }
    }

    override fun dismissSimpleDialog() {
        updateUiState { it.setSimpleDialogParam(null) }
    }

    private fun errorHandler(error: Throwable) {
        updateUiState { it.setSimpleDialogParam(getCommonSimpleDialogErrorParam(error)) }
    }
}