package com.composetest.feature.product.presenter.ui.list

import RatingStatus
import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.test.android.BaseTest
import com.composetest.core.test.kotlin.extension.runFlowTest
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.product.analytic.screen.ProductListScreenAnalytic
import com.composetest.feature.product.domain.model.ProductModel
import com.composetest.feature.product.domain.usecase.FilterProductsUseCase
import com.composetest.feature.product.domain.usecase.GetAllProductsUseCase
import com.composetest.feature.product.domain.usecase.ResyncProductsUseCase
import com.composetest.feature.product.navigation.destination.ProductDetailDestination
import com.composetest.feature.product.presenter.mapper.ProductDestinationlMapper
import com.composetest.feature.product.presenter.mapper.ProductItemListMapper
import com.composetest.feature.product.presenter.model.ProductItemListModel
import com.composetest.feature.product.presenter.ui.list.viewmodel.ProductListIntent
import com.composetest.feature.product.presenter.ui.list.viewmodel.ProductListUiEvent
import com.composetest.feature.product.presenter.ui.list.viewmodel.ProductListViewModel
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ProductListViewModelTest : BaseTest() {

    private val analyticSender: AnalyticSender = mockk(relaxed = true)
    private val getAllProductsUseCase: GetAllProductsUseCase = mockk()
    private val resyncProductsUseCase: ResyncProductsUseCase = mockk()
    private val filterProductsUseCase: FilterProductsUseCase = mockk()
    private val productItemListMapper: ProductItemListMapper = mockk()
    private val productDestinationlMapper: ProductDestinationlMapper = mockk()
    private lateinit var viewModel: ProductListViewModel

    override lateinit var testDispatcher: TestDispatcher

    private val asyncTaskUtils = AsyncTaskUtils(analyticSender, ProductListScreenAnalytic)

    private val fakeProduct = ProductModel(
        id = 1,
        title = "Test Product",
        description = "Test Description",
        price = 10.0,
        discountPercentage = 10.0,
        rating = 4.5,
        stock = 100,
        thumbnail = "Test Thumbnail",
    )

    @BeforeEach
    fun setUp() {
        coEvery { getAllProductsUseCase() } returns listOf(fakeProduct)
        coEvery { productItemListMapper.mapperTo(any()) } returns listOf(
            ProductItemListModel(
                id = 1,
                title = "Test Product",
                rating = "4.5",
                ratingStatus = RatingStatus.LIKE,
            )
        )
        viewModel = initViewModel()
    }

    @Test
    fun `initial uiState Should load product list and send analytic When initialized`() =
        runFlowTest(
            flow = viewModel.uiState,
            onVerify = {
                assertEquals(1, it.last().productItemList.size)
                coVerifySequence {
                    analyticSender.sendEvent(
                        CommonAnalyticEvent.OpenScreen(
                            ProductListScreenAnalytic
                        )
                    )
                    getAllProductsUseCase()
                }
            }
        )

    @Test
    fun `resyncProducts Should update product list When invoked`() = runFlowTest(
        flow = viewModel.uiState,
        onSetup = {
            coEvery { resyncProductsUseCase() } returns listOf(fakeProduct)
        },
        onTrigger = {
            viewModel.executeIntent(ProductListIntent.ResyncProducts)
        },
        onVerify = {
            assertTrue(it.last().productItemList.isNotEmpty())
            coVerifyOrder {
                resyncProductsUseCase()
                productItemListMapper.mapperTo(any())
            }
        }
    )

    @Test
    fun `navigateToDetail Should emit navigation event When product is found`() = runFlowTest(
        flow = viewModel.uiEvent,
        onSetup = {
            coEvery { productDestinationlMapper.mapperToDestination(any()) } returns ProductDetailDestination(
                title = "Test Product",
                description = "Test Description",
                price = 10.0,
                discountPercentage = 10.0,
                rating = 4.5,
                stock = 100,
                thumbnail = "Test Thumbnail",
            )
        },
        onTrigger = {
            viewModel.executeIntent(ProductListIntent.NavigateToDetail(1))
        },
        onVerify = {
            assertEquals(
                ProductListUiEvent.NavigateTo(
                    NavigationModel(
                        ProductDetailDestination(
                            title = "Test Product",
                            description = "Test Description",
                            price = 10.0,
                            discountPercentage = 10.0,
                            rating = 4.5,
                            stock = 100,
                            thumbnail = "Test Thumbnail",
                        )
                    )
                ),
                it.first()
            )
        }
    )

    @Test
    fun `productFilter Should update UI with filtered list When filter is applied`() = runFlowTest(
        flow = viewModel.uiState,
        onSetup = {
            val filtered = listOf(fakeProduct.copy(title = "Filtered"))
            coEvery { filterProductsUseCase(any(), "filter") } returns filtered
            coEvery { productItemListMapper.mapperTo(filtered) } returns listOf(
                ProductItemListModel(
                    id = 1,
                    title = "Test Product",
                    rating = "4.5",
                    ratingStatus = RatingStatus.LIKE,
                ),
            )
        },
        onTrigger = {
            viewModel.executeIntent(ProductListIntent.ProductFilter("filter"))
        },
        onVerify = {
            assertEquals("Test Product", it.last().productItemList.first().title)
        }
    )

    private fun initViewModel(): ProductListViewModel {
        return ProductListViewModel(
            analyticSender = analyticSender,
            getAllProductsUseCase = getAllProductsUseCase,
            resyncProductsUseCase = resyncProductsUseCase,
            filterProductsUseCase = filterProductsUseCase,
            productItemListMapper = productItemListMapper,
            productDestinationlMapper = productDestinationlMapper,
            asyncTaskUtils = asyncTaskUtils
        )
    }
}
