package com.composetest.feature.product.presenter.ui.list.viewmodel

import com.composetest.core.ui.interfaces.IntentReceiver

internal interface ProductListIntentReceiver : IntentReceiver<ProductListIntentReceiver> {
    fun resyncProducts()
    fun navigateToDetail(id: Int)
    fun productFilter(filter: String)
    fun dismissSimpleDialog()
}