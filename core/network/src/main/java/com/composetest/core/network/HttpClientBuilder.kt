package com.composetest.core.network

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
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

object HttpClientBuilder {
    private const val SCAPE_CHARACTER = "/"

    private val httpClient = HttpClient(Android) {
        expectSuccess = true
        defaultRequest {
            contentType(ContentType.Application.Json)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 20.seconds.inWholeMilliseconds
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
    }

    private val String.scapeCharacterTreatment
        get() = takeIf { it.isBlank() || it.endsWith(SCAPE_CHARACTER) } ?: plus(SCAPE_CHARACTER)

    fun build(apiSetting: ApiSetting) = httpClient.config {
        defaultRequest {
            url {
                url(apiSetting.url.scapeCharacterTreatment)
                appendPathSegments(apiSetting.path.scapeCharacterTreatment)
                port = apiSetting.port
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
}