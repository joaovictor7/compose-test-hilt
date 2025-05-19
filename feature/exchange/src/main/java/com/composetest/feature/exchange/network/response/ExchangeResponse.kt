package com.composetest.feature.exchange.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ExchangeResponse(
    @SerialName("exchange_id") val id: String,
    val website: String? = null,
    val name: String? = null,
    @SerialName("data_quote_start") val dateTimeQuoteStart: String? = null,
    @SerialName("data_quote_end") val dateTimeQuoteEnd: String? = null,
    @SerialName("data_orderbook_start") val dateTimeOrderBookStart: String? = null,
    @SerialName("data_orderbook_end") val dateTimeOrderBookEnd: String? = null,
    @SerialName("data_trade_start") val dateTimeOrderTradeStart: String? = null,
    @SerialName("data_trade_end") val dateTimeOrderTradeEnd: String? = null,
    @SerialName("data_symbols_count") val symbolsCount: Int,
    @SerialName("volume_1hrs_usd") val volume1hrsUsd: Double,
    @SerialName("volume_1day_usd") val volume1DayUsd: Double,
    @SerialName("volume_1mth_usd") val volume1MthUsd: Double,
    @SerialName("metric_id") val metricId: List<String>? = null,
    @SerialName("rank") val rank: Int,
)