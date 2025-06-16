package com.composetest.feature.exchange.data.di

import com.composetest.core.data.provider.AssetsProvider
import com.composetest.core.data.provider.EnvironmentInstanceProvider
import com.composetest.core.network.di.qualifier.ApiQualifier
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.exchange.data.datasource.CoinDataSource
import com.composetest.feature.exchange.data.datasource.local.CoinDataSourceFakeImpl
import com.composetest.feature.exchange.data.datasource.remote.CoinDataSourceImpl
import com.composetest.feature.exchange.network.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Provides
    fun coinDataSource(
        apiCallUtils: ApiCallUtils,
        assetsProvider: AssetsProvider,
        environmentInstanceProvider: EnvironmentInstanceProvider,
        @ApiQualifier(Api.COIN_API) httpClient: HttpClient
    ): CoinDataSource = environmentInstanceProvider.getInstance(
        instance = CoinDataSourceImpl(apiCallUtils, httpClient),
        fakeInstance = CoinDataSourceFakeImpl(apiCallUtils, assetsProvider)
    )
}