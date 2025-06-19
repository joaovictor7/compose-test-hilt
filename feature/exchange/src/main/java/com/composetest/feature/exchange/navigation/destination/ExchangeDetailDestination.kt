package com.composetest.feature.exchange.navigation.destination

import com.composetest.core.router.interfaces.Destination
import kotlinx.serialization.Serializable

@Serializable
internal data class ExchangeDetailDestination(
    val id: String,
    val website: String? = null,
    val name: String? = null,
    val dateTimeQuoteStart: String?,
    val dateTimeQuoteEnd: String?,
    val dateTimeOrderBookStart: String?,
    val dateTimeOrderBookEnd: String?,
    val dateTimeOrderTradeStart: String?,
    val dateTimeOrderTradeEnd: String?,
    val symbolsCount: Int,
    val volume1hrsUsd: Double,
    val volume1DayUsd: Double,
    val volume1MthUsd: Double,
    val metricId: List<String>?,
    val rank: Int,
) : Destination