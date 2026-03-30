package com.composetest.feature.product.presenter.mapper

import com.composetest.feature.product.R
import com.composetest.feature.product.navigation.navkey.ProductDetailNavKey
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

    fun mapperToModel(navKey: ProductDetailNavKey) = listOf(
        ProductDetailRow(R.string.product_detail_rating, navKey.rating.toString(), true),
        ProductDetailRow(R.string.product_detail_price, decimalFormat.format(navKey.price)),
        ProductDetailRow(
            R.string.product_detail_discount_percentage,
            "${navKey.discountPercentage}%"
        ),
        ProductDetailRow(R.string.product_detail_stock, navKey.stock.toString()),
    )
}