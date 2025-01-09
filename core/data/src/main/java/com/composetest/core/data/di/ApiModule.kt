package com.composetest.core.data.di

import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.data.api.ApiSetting
import com.composetest.core.data.di.qualifiers.ApiQualifier
import com.composetest.core.data.enums.Api
import com.composetest.core.data.extensions.configureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    @ApiQualifier(Api.NEWS_API)
    fun newsApi(buildConfigProvider: BuildConfigProvider): HttpClient = httpClient
        .configureApi(
            ApiSetting.NewsApi(
                apiKey = buildConfigProvider.get.buildConfigFieldsModel.newsApiKey,
                host = buildConfigProvider.get.buildConfigFieldsModel.newsApiKey,
                country = "us",
            )
        )

    @Provides
    @Singleton
    @ApiQualifier(Api.OPEN_WEATHER)
    fun openWeatherApi(buildConfigProvider: BuildConfigProvider): HttpClient = httpClient
        .configureApi(
            ApiSetting.OpenWeatherApi(
                apiId = buildConfigProvider.get.buildConfigFieldsModel.openWeatherApiKey,
                host = buildConfigProvider.get.buildConfigFieldsModel.openWeatherApiHost,
            )
        )
}

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