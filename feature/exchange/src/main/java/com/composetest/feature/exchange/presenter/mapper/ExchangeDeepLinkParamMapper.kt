package com.composetest.feature.exchange.presenter.mapper

import android.os.Bundle
import com.composetest.feature.exchange.navigation.param.ExchangeListDeepLinkParam

internal object ExchangeDeepLinkParamMapper {
    fun mapperToParam(bundle: Bundle?): ExchangeListDeepLinkParam? {
        val filter = bundle?.getString("filter") ?: return null
        return ExchangeListDeepLinkParam(
            filter = filter
        )
    }
}