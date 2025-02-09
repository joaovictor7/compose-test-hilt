package com.composetest.feature.exchange.ui.detail

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.exchange.ExchangeDetailDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.exchange.analytics.detail.ExchangeDetailScreenAnalytic
import com.composetest.feature.exchange.mappers.ExchangeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ExchangeDetailViewModel @Inject constructor(
    private val destination: ExchangeDetailDestination,
    private val exchangeMapper: ExchangeMapper,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<ExchangeDetailUiState, ExchangeDetailUiEvent>(
    ExchangeDetailUiState()
), ExchangeDetailCommandReceiver {

    override val commandReceiver = this

    override val analyticScreen = ExchangeDetailScreenAnalytic

    init {
        openScreenAnalytic()
        setDetails()
    }

    private fun setDetails() {
        updateUiState {
            val exchangeDetailsRowScreens = exchangeMapper(destination)
            it.setExchangeDataRowsScreen(exchangeDetailsRowScreens)
        }
    }
}