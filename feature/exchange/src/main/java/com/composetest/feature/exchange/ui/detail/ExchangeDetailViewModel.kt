package com.composetest.feature.exchange.ui.detail

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.router.destinations.exchange.ExchangeDetailDestination
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.analytic.events.exchange.ExchangeDetailScreenAnalytic
import com.composetest.feature.exchange.mappers.ExchangeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ExchangeDetailViewModel @Inject constructor(
    private val destination: ExchangeDetailDestination,
    private val exchangeMapper: ExchangeMapper,
    override val analyticSender: AnalyticSender,
) : BaseViewModel(), UiState<ExchangeDetailUiState> {

    override val analyticScreen = ExchangeDetailScreenAnalytic

    private val _uiState = MutableStateFlow(ExchangeDetailUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        openScreenAnalytic()
        setDetails()
    }

    private fun setDetails() {
        _uiState.update {
            val exchangeDetailsRowScreens = exchangeMapper.mapperToModels(destination)
            it.setExchangeDataRowsScreen(exchangeDetailsRowScreens)
        }
    }
}