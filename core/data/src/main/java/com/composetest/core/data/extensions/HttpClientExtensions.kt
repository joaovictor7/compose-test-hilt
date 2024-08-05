package com.composetest.core.data.extensions

import com.composetest.core.data.enums.NetworkApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post

internal fun HttpClient.setHost(
    host: String,
    port: Int = 0,
    networkApi: NetworkApi
) = apply {
    plugin(HttpSend).intercept { request ->
        request.url {
            this.host = host
            this.port = port
            this.protocol = networkApi.protocol
        }
        execute(request)
    }
}

internal suspend inline fun <reified Response> HttpClient.post(
    url: String,
    request: HttpRequestBuilder.() -> Unit
) = post(url, request).body<Response>()

internal suspend inline fun <reified Response> HttpClient.get(
    url: String,
    request: HttpRequestBuilder.() -> Unit = {}
) = get(url, request).body<Response>()