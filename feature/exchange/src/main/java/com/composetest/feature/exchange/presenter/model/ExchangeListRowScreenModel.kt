package com.composetest.feature.exchange.presenter.model

import androidx.annotation.StringRes

internal data class ExchangeListRowScreenModel(
    @param:StringRes val labelId: Int,
    val value: String,
)
