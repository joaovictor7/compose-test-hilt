package com.composetest.feature.product.presenter.model

import com.composetest.core.designsystem.component.rating.enums.RatingStatus

internal data class ProductItemListModel(
    val id: Int,
    val title: String,
    val rating: String,
    val ratingStatus: RatingStatus,
)