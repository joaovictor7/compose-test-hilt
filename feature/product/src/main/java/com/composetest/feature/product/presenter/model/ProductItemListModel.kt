package com.composetest.feature.product.presenter.model

import RatingStatus

internal data class ProductItemListModel(
    val id: Int,
    val title: String,
    val rating: String,
    val ratingStatus: RatingStatus,
)