package com.composetest.feature.exchange.network

import com.composetest.core.network.ApiSetting1

internal data class ExchangeApiSetting(
    private val apiKey: String,
    override val url: String,
) : ApiSetting1 {
    override val path = "v1/exchanges"
    override val headers = mapOf(API_KEY_HEADER to apiKey)

    private companion object {
        const val API_KEY_HEADER = "X-CoinAPI-Key"
    }
}