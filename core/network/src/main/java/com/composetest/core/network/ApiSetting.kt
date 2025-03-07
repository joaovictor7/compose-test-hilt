package com.composetest.core.network

sealed interface ApiSetting {
    val url: String
    val port: Int get() = 0
    val headers: Map<String, String> get() = emptyMap()
    val path: String get() = String()
    val queryParameters: Map<String, String> get() = emptyMap()

    data class NewsApi(
        private val apiKey: String,
        override val url: String
    ) : ApiSetting {
        override val headers = mapOf(API_KEY_HEADER to apiKey)

        private companion object {
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

interface ApiSetting1 {
    val url: String
    val port: Int get() = 0
    val headers: Map<String, String> get() = emptyMap()
    val path: String get() = String()
    val queryParameters: Map<String, String> get() = emptyMap()
}