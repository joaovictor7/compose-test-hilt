package com.composetest.feature.product.presenter.ui.list.viewmodel

import com.composetest.feature.product.presenter.model.ProductItemListModel

internal data class ProductListUiState(
    val filter: String = String(),
    val productItemList: List<ProductItemListModel> = emptyList(),
    val isLoading: Boolean = false
) {
    fun setIsLoading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun setProductScreenList(
        exchangeScreenList: List<ProductItemListModel>,
        filter: String = String(),
    ) = copy(
        productItemList = exchangeScreenList,
        filter = filter,
    )

    fun setProductListFiltered(
        exchangeFilter: String,
        exchangeScreenList: List<ProductItemListModel>
    ) = copy(filter = exchangeFilter, productItemList = exchangeScreenList)
}
