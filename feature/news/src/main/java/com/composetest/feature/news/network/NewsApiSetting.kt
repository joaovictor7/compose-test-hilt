package com.composetest.feature.news.network

import com.composetest.core.network.ApiSetting

data class NewsApiSetting(
    private val apiKey: String,
    override val url: String
) : ApiSetting {
    override val headers = mapOf(API_KEY_HEADER to apiKey)

    private companion object {
        const val API_KEY_HEADER = "x-api-key"
    }
}