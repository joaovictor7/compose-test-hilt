package com.composetest.core.domain.models.exchange

import java.time.LocalDateTime

data class ExchangeModel(
    val id: String,
    val website: String? = null,
    val name: String? = null,
    val dateTimeQuoteStart: LocalDateTime?,
    val dateTimeQuoteEnd: LocalDateTime?,
    val dateTimeOrderBookStart: LocalDateTime?,
    val dateTimeOrderBookEnd: LocalDateTime?,
    val dateTimeOrderTradeStart: LocalDateTime?,
    val dateTimeOrderTradeEnd: LocalDateTime?,
    val symbolsCount: Int,
    val volume1hrsUsd: Double,
    val volume1DayUsd: Double,
    val volume1MthUsd: Double,
    val metricId: List<String>?,
    val rank: Int,
)