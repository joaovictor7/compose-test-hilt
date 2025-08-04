package com.composetest.feature.product.presenter.mapper

import com.composetest.feature.product.domain.model.ProductModel
import com.composetest.feature.product.navigation.destination.ProductDetailDestination
import javax.inject.Inject

internal class ProductDestinationlMapper @Inject constructor() {

    fun mapperToDestination(model: ProductModel) = ProductDetailDestination(
        title = model.title,
        price = model.price,
        description = model.description,
        rating = model.rating,
        discountPercentage = model.discountPercentage,
        thumbnail = model.thumbnail,
        stock = model.stock,
    )
}