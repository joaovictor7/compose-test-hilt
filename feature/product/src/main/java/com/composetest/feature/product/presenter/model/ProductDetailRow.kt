package com.composetest.feature.product.presenter.model

import androidx.annotation.StringRes

internal data class ProductDetailRow(
    @param:StringRes val labelId: Int,
    val text: String,
    val isRating: Boolean = false,
)