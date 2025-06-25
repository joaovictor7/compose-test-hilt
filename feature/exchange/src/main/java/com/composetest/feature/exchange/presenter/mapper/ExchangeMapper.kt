package com.composetest.feature.exchange.presenter.mapper

import com.composetest.common.extension.convertFromString
import com.composetest.common.extension.convertToString
import com.composetest.core.ui.provider.StringResourceProvider
import com.composetest.feature.exchange.R
import com.composetest.feature.exchange.domain.model.ExchangeModel
import com.composetest.feature.exchange.navigation.destination.ExchangeDetailDestination
import com.composetest.feature.exchange.presenter.model.ExchangeDetailRowScreenModel
import com.composetest.feature.exchange.presenter.model.ExchangeListRowScreenModel
import com.composetest.feature.exchange.presenter.model.ExchangeScreenModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Inject

internal class ExchangeMapper @Inject constructor(
    private val stringResourceProvider: StringResourceProvider,
) {
    private val decimalFormatSymbols = DecimalFormatSymbols().apply {
        decimalSeparator = ','
        groupingSeparator = '.'
    }
    private val decimalFormat = DecimalFormat("$#,##0.00", decimalFormatSymbols)

    fun mapperToModels(allExchanges: List<ExchangeModel>) = allExchanges.map {
        ExchangeScreenModel(
            id = it.id,
            name = it.name,
            dataRows = listOf(
                ExchangeListRowScreenModel(
                    labelId = R.string.exchange_id_label,
                    value = it.id
                ),
                ExchangeListRowScreenModel(
                    labelId = R.string.exchange_volume_1day_label,
                    value = decimalFormat.format(it.volume1DayUsd)
                ),
            )
        )
    }

    fun mapperToModels(destination: ExchangeDetailDestination) = listOf(
        ExchangeDetailRowScreenModel(
            labelId = R.string.exchange_detail_id_label,
            value = destination.id
        ),
        ExchangeDetailRowScreenModel(
            labelId = R.string.exchange_detail_name_label,
            value = destination.name
        ),
        ExchangeDetailRowScreenModel(
            labelId = R.string.exchange_detail_rank_label,
            value = destination.rank.toString()
        ),
        ExchangeDetailRowScreenModel(
            labelId = R.string.exchange_detail_website_label,
            value = destination.website?.removeUrlSuffixes(),
            url = destination.website
        ),
        ExchangeDetailRowScreenModel(
            labelId = R.string.exchange_detail_symbols_count_label,
            value = destination.symbolsCount.toString()
        ),
        ExchangeDetailRowScreenModel(
            labelId = R.string.exchange_detail_metric_id_label,
            value = destination.metricId?.joinToString().takeIf { it?.isNotBlank() == true }
        ),
        ExchangeDetailRowScreenModel(
            labelId = R.string.exchange_detail_dates_label,
            gridValues = createDatesGridData(destination)
        ),
        ExchangeDetailRowScreenModel(
            labelId = R.string.exchange_detail_volumes_label,
            gridValues = createVolumesGridData(destination)
        ),
    )

    fun mapperToDestination(exchange: ExchangeModel?) = exchange?.let {
        ExchangeDetailDestination(
            id = it.id,
            website = it.website,
            name = it.name,
            dateTimeQuoteStart = it.dateTimeQuoteStart?.convertToString(DATE_TIME_FORMAT),
            dateTimeQuoteEnd = it.dateTimeQuoteEnd?.convertToString(DATE_TIME_FORMAT),
            dateTimeOrderTradeStart = it.dateTimeOrderTradeStart?.convertToString(DATE_TIME_FORMAT),
            dateTimeOrderTradeEnd = it.dateTimeOrderTradeEnd?.convertToString(DATE_TIME_FORMAT),
            dateTimeOrderBookStart = it.dateTimeOrderBookStart?.convertToString(DATE_TIME_FORMAT),
            dateTimeOrderBookEnd = it.dateTimeOrderBookEnd?.convertToString(DATE_TIME_FORMAT),
            symbolsCount = it.symbolsCount,
            volume1hrsUsd = it.volume1hrsUsd,
            volume1DayUsd = it.volume1DayUsd,
            volume1MthUsd = it.volume1MthUsd,
            metricId = it.metricId,
            rank = it.rank
        )
    }

    private fun createDatesGridData(destination: ExchangeDetailDestination) = listOf(
        listOf(
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_types_header_dates_grid),
                true
            ),
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_start_header_dates_grid),
                true
            ),
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_end_header_dates_grid),
                true
            ),
        ),
        listOf(
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_quotes_id_dates_grid),
            ),
            createCellGrid(destination.dateTimeQuoteStart?.dateTimeFormat()),
            createCellGrid(destination.dateTimeQuoteEnd?.dateTimeFormat()),
        ),
        listOf(
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_order_book_id_dates_grid),
            ),
            createCellGrid(destination.dateTimeOrderBookStart?.dateTimeFormat()),
            createCellGrid(destination.dateTimeOrderBookEnd?.dateTimeFormat()),
        ),
        listOf(
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_trade_id_dates_grid),
            ),
            createCellGrid(destination.dateTimeOrderTradeStart?.dateTimeFormat()),
            createCellGrid(destination.dateTimeOrderTradeEnd?.dateTimeFormat()),
        )
    )

    private fun createVolumesGridData(destination: ExchangeDetailDestination) = listOf(
        listOf(
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_volume_header_volume_grid),
                true
            ),
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_usd_header_volume_grid),
                true
            ),
        ),
        listOf(
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_hour_id_volume_grid),
            ),
            createCellGrid(decimalFormat.format(destination.volume1hrsUsd)),
        ),
        listOf(
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_day_id_volume_grid),
            ),
            createCellGrid(decimalFormat.format(destination.volume1DayUsd)),
        ),
        listOf(
            createCellGrid(
                stringResourceProvider.getString(R.string.exchange_detail_month_id_volume_grid),
            ),
            createCellGrid(decimalFormat.format(destination.volume1MthUsd)),
        )
    )

    private fun String?.removeUrlSuffixes() = this?.replaceFirst(
        Regex("^https?://(www\\.)?"), String()
    )?.removeSuffix("/")

    private fun String?.dateTimeFormat(): String? {
        val dateTime = this?.convertFromString(DATE_TIME_FORMAT)
        return if (dateTime?.hour == 0 && dateTime.minute == 0) {
            dateTime.convertToString(OUT_DATE_TIME_FORMAT3)
        } else {
            dateTime?.convertToString(OUT_DATE_TIME_FORMAT2)
        }
    }

    private fun createCellGrid(value: String?, highlights: Boolean = false) =
        value to highlights

    private companion object {
        const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'"
        const val OUT_DATE_TIME_FORMAT2 = "dd/MM/yyyy HH:mm"
        const val OUT_DATE_TIME_FORMAT3 = "dd/MM/yyyy"
    }
}