package com.composetest.core.data.di

import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.data.api.ApiSetting
import com.composetest.core.data.api.HttpClientBuilder
import com.composetest.core.data.di.qualifiers.ApiQualifier
import com.composetest.core.data.enums.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @ApiQualifier(Api.NEWS_API)
    fun newsApi(buildConfigProvider: BuildConfigProvider): HttpClient = HttpClientBuilder.build(
        ApiSetting.NewsApi(
            apiKey = buildConfigProvider.get.buildConfigFields.newsApiKey,
            url = buildConfigProvider.get.buildConfigFields.newsApiHost,
            country = "us",
        )
    )

    @Provides
    @ApiQualifier(Api.OPEN_WEATHER)
    fun openWeatherApi(buildConfigProvider: BuildConfigProvider): HttpClient =
        HttpClientBuilder.build(
            ApiSetting.OpenWeatherApi(
                apiId = buildConfigProvider.get.buildConfigFields.openWeatherApiKey,
                url = buildConfigProvider.get.buildConfigFields.openWeatherApiHost,
            )
        )
}