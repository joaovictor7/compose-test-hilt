package com.composetest.core.data.extensions

import com.composetest.core.data.api.ApiSetting
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post

internal fun HttpClient.configureApi(apiSetting: ApiSetting) = config {
    defaultRequest {
        url {
            url(apiSetting.url.takeIf { it.endsWith("/") } ?: apiSetting.url.plus("/"))
            port = apiSetting.port
            apiSetting.params.forEach {
                parameters.append(it.key, it.value)
            }
        }
        headers {
            apiSetting.headers.forEach {
                append(it.key, it.value)
            }
        }
    }
}

internal suspend inline fun <reified Response> HttpClient.post(
    path: String,
    request: HttpRequestBuilder.() -> Unit
) = post(path, request)

internal suspend inline fun <reified Response> HttpClient.get(
    path: String,
    request: HttpRequestBuilder.() -> Unit = {}
) = get(path, request).body<Response>()