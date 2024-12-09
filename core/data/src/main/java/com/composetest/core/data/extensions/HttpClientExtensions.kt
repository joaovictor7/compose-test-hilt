package com.composetest.core.data.extensions

import com.composetest.core.data.network.ApiConfiguration
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post

internal fun HttpClient.configureApi(apiConfiguration: ApiConfiguration) = config {
    defaultRequest {
        url {
            protocol = apiConfiguration.protocol
            host = apiConfiguration.host
            port = apiConfiguration.port
            apiConfiguration.params.forEach {
                parameters.append(it.key, it.value)
            }
        }
        headers {
            apiConfiguration.headers.forEach {
                append(it.key, it.value)
            }
        }
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