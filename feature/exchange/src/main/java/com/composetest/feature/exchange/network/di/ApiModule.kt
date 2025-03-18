package com.composetest.feature.exchange.network.di

import com.composetest.core.domain.providers.BuildConfigProvider
import com.composetest.core.domain.repositories.RemoteConfigRepository
import com.composetest.core.network.HttpClientBuilder
import com.composetest.core.network.di.qualifiers.ApiQualifier
import com.composetest.feature.exchange.network.ExchangeApiSetting
import com.composetest.feature.exchange.network.constants.ApiConstants
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
    @ApiQualifier(ApiConstants.COIN_API)
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