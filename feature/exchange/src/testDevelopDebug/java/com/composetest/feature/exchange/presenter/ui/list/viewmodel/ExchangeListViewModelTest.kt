package com.composetest.feature.exchange.presenter.ui.list.viewmodel

import app.cash.turbine.test
import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.designsystem.extension.dialogErrorNavigation
import com.composetest.core.network.model.ApiError
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.test.android.BaseTest
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.exchange.analytic.screen.ExchangeListScreenAnalytic
import com.composetest.feature.exchange.domain.model.ExchangeModel
import com.composetest.feature.exchange.domain.usecase.GetAllExchangesUseCase
import com.composetest.feature.exchange.navigation.navkey.ExchangeDetailNavKey
import com.composetest.feature.exchange.navigation.param.ExchangeListDeepLinkParam
import com.composetest.feature.exchange.presenter.mapper.ExchangeMapper
import com.composetest.feature.exchange.presenter.model.ExchangeScreenModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ExchangeListViewModelTest : BaseTest() {

    private val getAllExchangesUseCase: GetAllExchangesUseCase = mockk(relaxed = true)
    private val analyticSender: AnalyticSender = mockk(relaxed = true)
    private val exchangeMapper: ExchangeMapper = mockk {
        every { mapperToModels(any<List<ExchangeModel>>()) } returns emptyList()
        every { mapperToNavKey(any()) } returns null
    }

    private lateinit var viewModel: ExchangeListViewModel

    @BeforeEach
    fun before() {
        viewModel = initViewModel()
    }

    @Test
    fun `initial uiState`() = runTest {
        viewModel.uiState.test {
            assertEquals(ExchangeListUiState(), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        coVerify {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ExchangeListScreenAnalytic))
        }
    }

    @Test
    fun `getAllExchanges success`() = runTest {
        val exchanges = listOf(binanceModelMock)
        coEvery { getAllExchangesUseCase() } returns exchanges
        every { exchangeMapper.mapperToModels(exchanges) } returns listOf(binanceScreenModelMock)
        viewModel = initViewModel()

        viewModel.uiState.test {
            awaitItem().let { state ->
                assertFalse(state.isLoading)
                assertEquals(listOf(binanceScreenModelMock), state.exchangeScreenList)
                assertEquals(String(), state.exchangeFilter)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getAllExchanges network error`() = runTest {
        coEvery { getAllExchangesUseCase() } throws ApiError.Network()

        viewModel.uiEvent.test {
            viewModel.executeIntent(ExchangeListIntent.GetAllExchanges)
            assertEquals(
                ExchangeListUiEvent.NavigateTo(ApiError.Network().dialogErrorNavigation()),
                awaitItem()
            )
        }
    }

    @Test
    fun `navigateToDetail - exchange found`() = runTest {
        coEvery { getAllExchangesUseCase() } returns listOf(binanceModelMock)
        every { exchangeMapper.mapperToNavKey(binanceModelMock) } returns binanceDetailNavKeyMock
        viewModel = initViewModel()

        viewModel.uiEvent.test {
            viewModel.executeIntent(ExchangeListIntent.NavigateToDetail(binanceModelMock.id))
            assertEquals(
                ExchangeListUiEvent.NavigateTo(NavigationModel(binanceDetailNavKeyMock)),
                awaitItem()
            )
        }
    }

    @Test
    fun `exchangeFilter filters exchange list by name`() = runTest {
        val allExchanges = listOf(binanceModelMock, krakenModelMock)
        coEvery { getAllExchangesUseCase() } returns allExchanges
        every { exchangeMapper.mapperToModels(allExchanges) } returns listOf(binanceScreenModelMock, krakenScreenModelMock)
        every { exchangeMapper.mapperToModels(listOf(binanceModelMock)) } returns listOf(binanceScreenModelMock)
        viewModel = initViewModel()

        viewModel.executeIntent(ExchangeListIntent.ExchangeFilter("Binance"))

        viewModel.uiState.test {
            awaitItem().let { state ->
                assertEquals("Binance", state.exchangeFilter)
                assertEquals(listOf(binanceScreenModelMock), state.exchangeScreenList)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `exchangeFilter with empty string returns full list`() = runTest {
        val allExchanges = listOf(binanceModelMock, krakenModelMock)
        val allScreenModels = listOf(binanceScreenModelMock, krakenScreenModelMock)
        coEvery { getAllExchangesUseCase() } returns allExchanges
        every { exchangeMapper.mapperToModels(allExchanges) } returns allScreenModels
        viewModel = initViewModel()

        viewModel.executeIntent(ExchangeListIntent.ExchangeFilter(""))

        viewModel.uiState.test {
            awaitItem().let { state ->
                assertEquals("", state.exchangeFilter)
                assertEquals(allScreenModels, state.exchangeScreenList)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `deepLinkParam applies filter on init`() = runTest {
        val allExchanges = listOf(binanceModelMock, krakenModelMock)
        coEvery { getAllExchangesUseCase() } returns allExchanges
        every { exchangeMapper.mapperToModels(allExchanges) } returns listOf(binanceScreenModelMock, krakenScreenModelMock)
        every { exchangeMapper.mapperToModels(listOf(binanceModelMock)) } returns listOf(binanceScreenModelMock)

        viewModel = initViewModel(deepLinkParam = ExchangeListDeepLinkParam(filter = "Binance"))

        viewModel.uiState.test {
            awaitItem().let { state ->
                assertEquals("Binance", state.exchangeFilter)
                assertEquals(listOf(binanceScreenModelMock), state.exchangeScreenList)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    private fun initViewModel(deepLinkParam: ExchangeListDeepLinkParam? = null) =
        ExchangeListViewModel(
            getAllExchangesUseCase = getAllExchangesUseCase,
            exchangeMapper = exchangeMapper,
            analyticSender = analyticSender,
            deepLinkParam = deepLinkParam,
            asyncTaskUtils = AsyncTaskUtils(analyticSender, ExchangeListScreenAnalytic),
        )

    private companion object {
        val binanceModelMock = ExchangeModel(
            id = "BINANCE",
            name = "Binance",
            website = "https://www.binance.com",
            dateTimeQuoteStart = null,
            dateTimeQuoteEnd = null,
            dateTimeOrderBookStart = null,
            dateTimeOrderBookEnd = null,
            dateTimeOrderTradeStart = null,
            dateTimeOrderTradeEnd = null,
            symbolsCount = 1500,
            volume1hrsUsd = 1_000_000.0,
            volume1DayUsd = 24_000_000.0,
            volume1MthUsd = 720_000_000.0,
            metricId = listOf("VCCR"),
            rank = 1,
        )

        val krakenModelMock = ExchangeModel(
            id = "KRAKEN",
            name = "Kraken",
            website = "https://www.kraken.com",
            dateTimeQuoteStart = null,
            dateTimeQuoteEnd = null,
            dateTimeOrderBookStart = null,
            dateTimeOrderBookEnd = null,
            dateTimeOrderTradeStart = null,
            dateTimeOrderTradeEnd = null,
            symbolsCount = 800,
            volume1hrsUsd = 500_000.0,
            volume1DayUsd = 12_000_000.0,
            volume1MthUsd = 360_000_000.0,
            metricId = null,
            rank = 2,
        )

        val binanceDetailNavKeyMock = ExchangeDetailNavKey(
            id = "BINANCE",
            name = "Binance",
            website = "https://www.binance.com",
            dateTimeQuoteStart = null,
            dateTimeQuoteEnd = null,
            dateTimeOrderBookStart = null,
            dateTimeOrderBookEnd = null,
            dateTimeOrderTradeStart = null,
            dateTimeOrderTradeEnd = null,
            symbolsCount = 1500,
            volume1hrsUsd = 1_000_000.0,
            volume1DayUsd = 24_000_000.0,
            volume1MthUsd = 720_000_000.0,
            metricId = listOf("VCCR"),
            rank = 1,
        )

        val binanceScreenModelMock = ExchangeScreenModel(
            id = "BINANCE",
            name = "Binance",
            dataRows = emptyList()
        )

        val krakenScreenModelMock = ExchangeScreenModel(
            id = "KRAKEN",
            name = "Kraken",
            dataRows = emptyList()
        )
    }
}
