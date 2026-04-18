package com.composetest.feature.product.presenter.ui.form.viewmodel

import app.cash.turbine.test
import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.test.android.BaseTest
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.product.R
import com.composetest.feature.product.analytic.screen.ProductDetailScreenAnalytic
import com.composetest.feature.product.navigation.navkey.ProductDetailNavKey
import com.composetest.feature.product.presenter.mapper.ProductDetailMapper
import com.composetest.feature.product.presenter.model.ProductDetailRow
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ProductDetailViewModelTest : BaseTest() {

    private val analyticSender: AnalyticSender = mockk(relaxed = true)
    private val productDetailMapper: ProductDetailMapper = mockk()
    private lateinit var viewModel: ProductDetailViewModel

    private val asyncTaskUtils = AsyncTaskUtils(analyticSender, ProductDetailScreenAnalytic)

    @BeforeEach
    fun setUp() {
        every { productDetailMapper.mapperToModel(navKeyMock) } returns listOf(
            ProductDetailRow(
                R.string.product_detail_rating,
                navKeyMock.rating.toString(),
                true
            ),
            ProductDetailRow(R.string.product_detail_price, "$199.99"),
            ProductDetailRow(
                R.string.product_detail_discount_percentage,
                "${navKeyMock.discountPercentage}%"
            ),
            ProductDetailRow(R.string.product_detail_stock, navKeyMock.stock.toString()),
        )
        viewModel = initViewModel()
    }

    @Test
    fun `init Should update uiState with product details and send analytics`() = runTest {
        viewModel.uiState.test {
            awaitItem().let { state ->
                assertEquals(navKeyMock.title, state.title)
                assertEquals(navKeyMock.thumbnail, state.thumbnail)
                assertEquals(navKeyMock.description, state.description)
                assertEquals(detailModelMock, state.infoRows)
            }
            cancelAndConsumeRemainingEvents()
        }
        coVerify {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ProductDetailScreenAnalytic))
        }
    }

    private fun initViewModel() = ProductDetailViewModel(
        navKey = navKeyMock,
        productDetailMapper = productDetailMapper,
        analyticSender = analyticSender,
        asyncTaskUtils = asyncTaskUtils,
    )

    private companion object {
        val navKeyMock = ProductDetailNavKey(
            title = "Product Title",
            thumbnail = "http://image.com/image.png",
            description = "Product Description",
            price = 199.99,
            discountPercentage = 0.0,
            rating = 0.0,
            stock = 2,
        )

        val detailModelMock = listOf(
            ProductDetailRow(
                R.string.product_detail_rating,
                navKeyMock.rating.toString(),
                true
            ),
            ProductDetailRow(R.string.product_detail_price, "$199.99"),
            ProductDetailRow(
                R.string.product_detail_discount_percentage,
                "${navKeyMock.discountPercentage}%"
            ),
            ProductDetailRow(R.string.product_detail_stock, navKeyMock.stock.toString()),
        )
    }
}
