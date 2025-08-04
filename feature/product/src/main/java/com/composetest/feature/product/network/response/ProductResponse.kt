package com.composetest.feature.product.network.response

import kotlinx.serialization.Serializable

@Serializable
internal data class ProductResponse(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String
)