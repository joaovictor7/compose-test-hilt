package com.composetest.feature.exchange.presenter.model

internal data class ExchangeScreenModel(
    val id: String,
    val name: String?,
    val dataRows: List<ExchangeListRowScreenModel>
)
