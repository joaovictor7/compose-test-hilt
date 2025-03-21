package com.composetest.feature.exchange.ui.detail

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.router.destinations.exchange.ExchangeDetailDestination
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.utils.AsyncTaskUtils
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
    private val analyticSender: AnalyticSender,
    @AsyncTaskUtilsQualifier(ScreensAnalytic.EXCHANGE_DETAIL) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<ExchangeDetailUiState> {

    private val _uiState = MutableStateFlow(ExchangeDetailUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        sendOpenScreenAnalytic()
        setDetails()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ScreensAnalytic.EXCHANGE_DETAIL))
        }
    }

    private fun setDetails() {
        _uiState.update {
            val exchangeDetailsRowScreens = exchangeMapper.mapperToModels(destination)
            it.setExchangeDataRowsScreen(exchangeDetailsRowScreens)
        }
    }
}