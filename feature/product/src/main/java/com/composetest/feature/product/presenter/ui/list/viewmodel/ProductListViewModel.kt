package com.composetest.feature.product.presenter.ui.list.viewmodel

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.product.analytic.screen.ProductListScreenAnalytic
import com.composetest.feature.product.domain.model.ProductModel
import com.composetest.feature.product.domain.usecase.FilterProductsUseCase
import com.composetest.feature.product.domain.usecase.GetAllProductsUseCase
import com.composetest.feature.product.domain.usecase.ResyncProductsUseCase
import com.composetest.feature.product.presenter.mapper.ProductItemListMapper
import com.composetest.feature.product.presenter.mapper.ProductNavKeyMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductListViewModel @Inject constructor(
    private val analyticSender: AnalyticSender,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val resyncProductsUseCase: ResyncProductsUseCase,
    private val filterProductsUseCase: FilterProductsUseCase,
    private val productItemListMapper: ProductItemListMapper,
    private val productNavKeyMapper: ProductNavKeyMapper,
    @param:AsyncTaskUtilsQualifier(ProductListScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(),
    UiState<ProductListUiState>,
    UiEvent<ProductListUiEvent>,
    ProductListIntentReceiver {

    override val intentReceiver = this

    private var productList = emptyList<ProductModel>()

    private val _uiState = MutableStateFlow(ProductListUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ProductListUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        sendOpenScreenAnalytic()
        getAllProducts()
    }

    override fun sendOpenScreenAnalytic() {
        viewModelScope.launch {
            asyncTaskUtils.runAsyncTask {
                analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ProductListScreenAnalytic))
            }
        }
    }

    override fun resyncProducts() {
        _uiState.update { it.setIsLoading(true) }
        viewModelScope.launch {
            asyncTaskUtils.runAsyncTask(
                onError = { errorHandler(it) },
                onCompletion = { _uiState.update { it.setIsLoading(false) } }
            ) {
                productList = resyncProductsUseCase()
                _uiState.update { it.setProductScreenList(productItemListMapper.mapperTo(productList)) }
            }
        }
    }

    override fun navigateToDetail(id: Int) {
        val product = productList.find { it.id == id } ?: return
        val navKey = productNavKeyMapper.mapperToNavKey(product)
        _uiEvent.emitEvent(ProductListUiEvent.NavigateTo(NavigationModel(navKey)))
    }

    override fun productFilter(filter: String) {
        val productsFiltered = filterProductsUseCase(productList, filter)
        _uiState.update {
            it.setProductScreenList(
                productItemListMapper.mapperTo(productsFiltered),
                filter,
            )
        }
    }

    override fun dismissSimpleDialog() {
//        _uiState.update { it.setSimpleDialogParam(null) }
    }

    private fun getAllProducts() {
        _uiState.update { it.setIsLoading(true) }
        viewModelScope.launch {
            asyncTaskUtils.runAsyncTask(
                onError = ::errorHandler,
                onCompletion = { _uiState.update { it.setIsLoading(false) } }
            ) {
                productList = getAllProductsUseCase()
                _uiState.update { it.setProductScreenList(productItemListMapper.mapperTo(productList)) }
            }
        }
    }

    private fun errorHandler(error: Throwable) {
//        _uiEvent.emitEvent(ProductListUiEvent.NavigateTo(error.dialogErrorDestination()))
    }
}