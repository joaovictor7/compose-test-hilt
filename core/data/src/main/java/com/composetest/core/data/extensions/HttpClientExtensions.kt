package com.composetest.core.data.extensions

import com.composetest.core.data.api.ApiSetting
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.URLProtocol

internal fun HttpClient.configureApi(apiSetting: ApiSetting) = config {
    defaultRequest {
        url {
            val protocol = apiSetting.url.removePrefix("${URLProtocol.HTTP.name}://")
            host = apiSetting.url
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
    url: String,
    request: HttpRequestBuilder.() -> Unit
) = post(url, request).body<Response>()

internal suspend inline fun <reified Response> HttpClient.get(
    url: String,
    request: HttpRequestBuilder.() -> Unit = {}
) = get(url, request).body<Response>()