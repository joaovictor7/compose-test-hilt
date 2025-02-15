package com.composetest.feature.exchange.ui.detail

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.exchange.ExchangeDetailDestination
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiState
import com.composetest.feature.exchange.analytics.detail.ExchangeDetailScreenAnalytic
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
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
) : BaseViewModel(), UiState<ExchangeDetailUiState> {

    private val _uiState = MutableStateFlow(ExchangeDetailUiState())

    override val uiState = _uiState.asStateFlow()
    override val analyticScreen = ExchangeDetailScreenAnalytic

    init {
        openScreenAnalytic()
        setDetails()
    }

    private fun setDetails() {
        _uiState.update {
            val exchangeDetailsRowScreens = exchangeMapper(destination)
            it.setExchangeDataRowsScreen(exchangeDetailsRowScreens)
        }
    }
}