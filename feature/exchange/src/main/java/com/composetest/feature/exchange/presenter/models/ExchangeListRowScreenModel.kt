package com.composetest.feature.exchange.presenter.models

import androidx.annotation.StringRes

internal data class ExchangeListRowScreenModel(
    @StringRes val labelId: Int,
    val value: String,
)
