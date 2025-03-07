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
}

interface ApiSetting1 {
    val url: String
    val port: Int get() = 0
    val headers: Map<String, String> get() = emptyMap()
    val path: String get() = String()
    val queryParameters: Map<String, String> get() = emptyMap()
}