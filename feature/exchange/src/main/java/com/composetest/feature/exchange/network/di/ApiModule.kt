package com.composetest.feature.exchange.network.di

import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.domain.repository.RemoteConfigRepository
import com.composetest.core.network.HttpClientBuilder
import com.composetest.core.network.di.qualifier.ApiQualifier
import com.composetest.feature.exchange.network.ExchangeApiSetting
import com.composetest.feature.exchange.network.api.Api
import com.composetest.feature.exchange.network.enums.ApiKeyRemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @ApiQualifier(Api.COIN_API)
    fun coinApi(
        remoteConfigRepository: RemoteConfigRepository,
        buildConfigProvider: BuildConfigProvider
    ): HttpClient = HttpClientBuilder.build(
        ExchangeApiSetting(
            apiKey = remoteConfigRepository.getString(ApiKeyRemoteConfig.COIN_API),
            url = buildConfigProvider.buildConfigFields.coinApiHost,
        )
    )
}