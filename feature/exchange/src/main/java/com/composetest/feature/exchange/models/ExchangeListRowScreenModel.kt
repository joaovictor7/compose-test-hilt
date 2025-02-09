package com.composetest.feature.exchange.models

import androidx.annotation.StringRes

internal data class ExchangeListRowScreenModel(
    @StringRes val labelId: Int,
    val value: String,
)
