package com.composetest.feature.product.navigation.destination

import com.composetest.core.router.interfaces.Destination
import kotlinx.serialization.Serializable

@Serializable
internal data class ProductDetailDestination(
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
) : Destination