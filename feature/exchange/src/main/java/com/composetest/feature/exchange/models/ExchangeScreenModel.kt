package com.composetest.feature.exchange.models

internal data class ExchangeScreenModel(
    val id: String,
    val name: String?,
    val dataRows: List<ExchangeListRowScreenModel>
)
