package com.composetest.feature.product.presenter.mapper

import com.composetest.feature.product.domain.model.ProductModel
import com.composetest.feature.product.navigation.navkey.ProductDetailNavKey
import javax.inject.Inject

internal class ProductNavKeyMapper @Inject constructor() {

    fun mapperToNavKey(model: ProductModel) = ProductDetailNavKey(
        title = model.title,
        price = model.price,
        description = model.description,
        rating = model.rating,
        discountPercentage = model.discountPercentage,
        thumbnail = model.thumbnail,
        stock = model.stock,
    )
}