package com.composetest.core.network

interface ApiSetting {
    val url: String
    val port: Int get() = 0
    val headers: Map<String, String> get() = emptyMap()
    val path: String get() = String()
    val queryParameters: Map<String, String> get() = emptyMap()
}