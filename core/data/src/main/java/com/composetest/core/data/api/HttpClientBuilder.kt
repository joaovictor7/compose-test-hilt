package com.composetest.core.data.api

import com.composetest.core.data.utils.jsonConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlin.time.Duration.Companion.seconds

internal object HttpClientBuilder {
    private const val SCAPE_CHARACTERS = "/"

    private val httpClient = HttpClient(Android) {
        expectSuccess = true
        defaultRequest {
            contentType(ContentType.Application.Json)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 20.seconds.inWholeMilliseconds
        }
        install(ContentNegotiation) {
            json(jsonConfig)
        }
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
    }

    private val String.urlTreatment
        get() = takeIf { it.endsWith(SCAPE_CHARACTERS) } ?: plus(SCAPE_CHARACTERS)

    fun build(apiSetting: ApiSetting) = httpClient.config {
        defaultRequest {
            url {
                url(apiSetting.url.urlTreatment)
                port = apiSetting.port
                apiSetting.pathParameters.forEachIndexed { index, value ->
                    appendPathSegments(
                        value.pathSegmentTreatment(index, apiSetting.pathParameters.lastIndex)
                    )
                }
                apiSetting.queryParameters.forEach {
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

    private fun String.pathSegmentTreatment(index: Int, lastIndex: Int) = also {
        if (index == lastIndex) return plus(SCAPE_CHARACTERS)
    }
}