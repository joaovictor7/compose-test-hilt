package com.composetest.feature.product.presenter.mapper

import com.composetest.feature.product.R
import com.composetest.feature.product.navigation.destination.ProductDetailDestination
import com.composetest.feature.product.presenter.model.ProductDetailRow
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Inject

internal class ProductDetailMapper @Inject constructor() {
    private val decimalFormatSymbols = DecimalFormatSymbols().apply {
        decimalSeparator = ','
        groupingSeparator = '.'
    }
    private val decimalFormat = DecimalFormat("$#,##0.00", decimalFormatSymbols)

    fun mapperToModel(destination: ProductDetailDestination) = listOf(
        ProductDetailRow(R.string.product_detail_rating, destination.rating.toString(), true),
        ProductDetailRow(R.string.product_detail_price, decimalFormat.format(destination.price)),
        ProductDetailRow(
            R.string.product_detail_discount_percentage,
            "${destination.discountPercentage}%"
        ),
        ProductDetailRow(R.string.product_detail_stock, destination.stock.toString()),
    )
}