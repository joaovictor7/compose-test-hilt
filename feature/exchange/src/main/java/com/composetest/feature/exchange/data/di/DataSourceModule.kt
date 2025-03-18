package com.composetest.feature.exchange.data.di

import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.providers.EnvironmentInstanceProvider
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.core.network.di.qualifiers.ApiQualifier
import com.composetest.feature.exchange.data.datasources.CoinDataSource
import com.composetest.feature.exchange.data.datasources.local.CoinDataSourceFakeImpl
import com.composetest.feature.exchange.data.datasources.remote.CoinDataSourceImpl
import com.composetest.feature.exchange.network.constants.ApiConstants
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
        @ApiQualifier(ApiConstants.COIN_API) httpClient: HttpClient
    ): CoinDataSource = environmentInstanceProvider.getInstance(
        instance = CoinDataSourceImpl(apiCallUtils, httpClient),
        fakeInstance = CoinDataSourceFakeImpl(apiCallUtils, assetsProvider)
    )
}