package com.composetest.feature.product.presenter.ui.form.viewmodel

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.product.analytic.screen.ProductDetailScreenAnalytic
import com.composetest.feature.product.navigation.destination.ProductDetailDestination
import com.composetest.feature.product.presenter.mapper.ProductDetailMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailViewModel @Inject constructor(
    private val destination: ProductDetailDestination,
    private val productDetailMapper: ProductDetailMapper,
    private val analyticSender: AnalyticSender,
    @param:AsyncTaskUtilsQualifier(ProductDetailScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<ProductDetailUiState> {

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        sendOpenScreenAnalytic()
        setDetails()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ProductDetailScreenAnalytic))
        }
    }

    private fun setDetails() {
        _uiState.update {
            it.setProductDetails(
                destination.thumbnail,
                destination.title,
                destination.description,
                productDetailMapper.mapperToModel(destination)
            )
        }
    }
}