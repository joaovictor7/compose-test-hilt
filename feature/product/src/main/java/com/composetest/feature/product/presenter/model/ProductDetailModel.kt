package com.composetest.feature.product.presenter.model

internal data class ProductDetailModel(
    val title: String,
    val description: String,
    val price: String,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
)