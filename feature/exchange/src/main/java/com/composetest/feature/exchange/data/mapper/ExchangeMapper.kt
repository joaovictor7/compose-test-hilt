package com.composetest.feature.exchange.data.mapper

import com.composetest.common.extension.convertFromString
import com.composetest.feature.exchange.domain.model.ExchangeModel
import com.composetest.feature.exchange.network.response.ExchangeResponse
import javax.inject.Inject

internal class ExchangeMapper @Inject constructor() {
    fun mapperToModels(response: List<ExchangeResponse>) = response.map {
        ExchangeModel(
            id = it.id,
            website = it.website,
            name = it.name,
            dateTimeQuoteStart = it.dateTimeQuoteStart?.convertFromString(DATE_TIME_FORMAT),
            dateTimeQuoteEnd = it.dateTimeQuoteEnd?.convertFromString(DATE_TIME_FORMAT),
            dateTimeOrderBookStart = it.dateTimeOrderBookStart?.convertFromString(DATE_TIME_FORMAT),
            dateTimeOrderBookEnd = it.dateTimeOrderBookStart?.convertFromString(DATE_TIME_FORMAT),
            dateTimeOrderTradeStart = it.dateTimeOrderTradeStart?.convertFromString(DATE_TIME_FORMAT),
            dateTimeOrderTradeEnd = it.dateTimeOrderTradeEnd?.convertFromString(DATE_TIME_FORMAT),
            symbolsCount = it.symbolsCount,
            volume1hrsUsd = it.volume1hrsUsd,
            volume1DayUsd = it.volume1DayUsd,
            volume1MthUsd = it.volume1MthUsd,
            metricId = it.metricId,
            rank = it.rank,
        )
    }

    private companion object {
        const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'"
    }
}