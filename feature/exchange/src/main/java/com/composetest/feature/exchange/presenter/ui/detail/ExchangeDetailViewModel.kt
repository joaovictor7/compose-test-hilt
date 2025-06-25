package com.composetest.feature.exchange.presenter.ui.detail

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.event.CommonAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.exchange.analytic.screen.ExchangeDetailScreenAnalytic
import com.composetest.feature.exchange.navigation.destination.ExchangeDetailDestination
import com.composetest.feature.exchange.presenter.mapper.ExchangeMapper
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
    @param:AsyncTaskUtilsQualifier(ExchangeDetailScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<ExchangeDetailUiState> {

    private val _uiState = MutableStateFlow(ExchangeDetailUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        sendOpenScreenAnalytic()
        setDetails()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ExchangeDetailScreenAnalytic))
        }
    }

    private fun setDetails() {
        _uiState.update {
            val exchangeDetailsRowScreens = exchangeMapper.mapperToModels(destination)
            it.setExchangeDataRowsScreen(exchangeDetailsRowScreens)
        }
    }
}