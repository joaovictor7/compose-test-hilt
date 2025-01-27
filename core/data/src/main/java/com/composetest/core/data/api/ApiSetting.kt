package com.composetest.core.data.api

internal sealed interface ApiSetting {
    val url: String
    val port: Int get() = 0
    val headers: Map<String, String> get() = emptyMap()
    val pathParameters: List<String> get() = emptyList()
    val queryParameters: Map<String, String> get() = emptyMap()

    data class NewsApi(
        private val apiKey: String,
        private val country: String,
        override val url: String
    ) : ApiSetting {
        override val headers = mapOf(API_KEY_HEADER to apiKey)
        override val queryParameters = mapOf(COUNTRY_PARAM to country)

        private companion object {
            const val COUNTRY_PARAM = "country"
            const val API_KEY_HEADER = "x-api-key"
        }
    }

    data class OpenWeatherApi(
        private val apiId: String,
        override val url: String,
    ) : ApiSetting {
        override val queryParameters = mapOf(API_ID_PARAM to apiId)

        private companion object {
            const val API_ID_PARAM = "appid"
        }
    }
}