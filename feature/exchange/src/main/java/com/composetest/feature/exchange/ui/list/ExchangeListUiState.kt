package com.composetest.feature.exchange.ui.list

import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.exchange.models.ExchangeScreenModel

internal data class ExchangeListUiState(
    val exchangeFilter: String = String(),
    val exchangeScreenList: List<ExchangeScreenModel> = emptyList(),
    val simpleDialogParam: SimpleDialogParam? = null,
    val isLoading: Boolean = false
) : BaseUiState {
    fun setIsLoading(isLoading: Boolean) = copy(isLoading = isLoading)
    fun setExchangeScreenList(exchangeScreenList: List<ExchangeScreenModel>) =
        copy(
            exchangeScreenList = exchangeScreenList,
            exchangeFilter = String(),
        )

    fun setExchangeListFiltered(
        exchangeFilter: String,
        exchangeScreenList: List<ExchangeScreenModel>
    ) = copy(exchangeFilter = exchangeFilter, exchangeScreenList = exchangeScreenList)

    fun setSimpleDialogParam(simpleDialogParam: SimpleDialogParam?) =
        copy(simpleDialogParam = simpleDialogParam)
}
