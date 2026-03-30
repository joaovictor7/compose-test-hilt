package com.composetest.feature.product.navigation.navkey

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
internal data class ProductDetailNavKey(
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
) : NavKey