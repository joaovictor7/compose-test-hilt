package com.composetest.feature.product.data.datasource

import com.composetest.core.data.api.extension.readJsonAs
import com.composetest.core.data.api.provider.AssetsProvider
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.product.network.response.ProductsResponse

internal class ProductRemoteFakeDataSource(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider,
) : ProductRemoteDataSource {

    override suspend fun getAllProducts() = apiCallUtils.executeApiCall {
        assetsProvider.readJsonAs<ProductsResponse>(PRODUCTS_JSON_FILE_NAME)
    }

    private companion object Companion {
        const val PRODUCTS_JSON_FILE_NAME = "all-products"
    }
}