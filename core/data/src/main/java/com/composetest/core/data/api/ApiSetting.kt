package com.composetest.core.data.api

import io.ktor.http.URLProtocol

internal sealed interface ApiSetting {
    val host: String
    val protocol: URLProtocol get() = URLProtocol.HTTPS
    val port: Int get() = 0
    val headers: Map<String, String> get() = emptyMap()
    val params: Map<String, String> get() = emptyMap()

    data class NewsApi(
        private val apiKey: String,
        private val country: String,
        override val host: String
    ) : ApiSetting {
        override val headers = mapOf(API_KEY_HEADER to apiKey)
        override val params = mapOf(COUNTRY_PARAM to country)

        private companion object {
            const val COUNTRY_PARAM = "country"
            const val API_KEY_HEADER = "x-api-key"
        }
    }
}