package com.composetest.feature.product.data.datasource

import com.composetest.feature.product.network.response.ProductsResponse

internal interface ProductRemoteDataSource {

    suspend fun getAllProducts(): ProductsResponse
}