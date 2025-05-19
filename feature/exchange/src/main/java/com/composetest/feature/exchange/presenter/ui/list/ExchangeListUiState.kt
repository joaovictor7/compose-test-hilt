package com.composetest.feature.exchange.presenter.ui.list

import com.composetest.core.designsystem.param.alertdialog.SimpleDialogParam
import com.composetest.feature.exchange.presenter.model.ExchangeScreenModel

internal data class ExchangeListUiState(
    val exchangeFilter: String = String(),
    val exchangeScreenList: List<ExchangeScreenModel> = emptyList(),
    val simpleDialogParam: SimpleDialogParam? = null,
    val isLoading: Boolean = false
) {
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
