package com.composetest.feature.exchange.models

import androidx.annotation.StringRes

internal data class ExchangeDetailRowScreenModel(
    @StringRes val labelId: Int,
    val value: String? = null,
    val gridValues: List<List<Pair<String?, Boolean>>> = emptyList(),
    val url: String? = null,
) {
    val isLink get() = url != null
    val valueIsGrid get() = gridValues.isNotEmpty()
}
