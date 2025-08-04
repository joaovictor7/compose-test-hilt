package com.composetest.feature.product.presenter.ui.list.viewmodel

import com.composetest.core.ui.interfaces.Intent

internal sealed interface ProductListIntent : Intent<ProductListIntentReceiver> {
    data object ResyncProducts : ProductListIntent {
        override fun execute(intentReceiver: ProductListIntentReceiver) {
            intentReceiver.resyncProducts()
        }
    }

    data object DismissSimpleDialog : ProductListIntent {
        override fun execute(intentReceiver: ProductListIntentReceiver) {
            intentReceiver.dismissSimpleDialog()
        }
    }

    data class NavigateToDetail(private val id: Int) : ProductListIntent {
        override fun execute(intentReceiver: ProductListIntentReceiver) {
            intentReceiver.navigateToDetail(id)
        }
    }

    data class ProductFilter(private val exchange: String) : ProductListIntent {
        override fun execute(intentReceiver: ProductListIntentReceiver) {
            intentReceiver.productFilter(exchange)
        }
    }
}