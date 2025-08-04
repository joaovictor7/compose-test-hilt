package com.composetest.feature.product.network

import com.composetest.core.network.ApiSetting

internal data class ProductApiSetting(
    override val url: String,
) : ApiSetting