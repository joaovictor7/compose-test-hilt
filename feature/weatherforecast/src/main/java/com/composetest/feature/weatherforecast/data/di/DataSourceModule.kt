package com.composetest.feature.weatherforecast.data.di

import com.composetest.core.data.provider.AssetsProvider
import com.composetest.core.data.provider.EnvironmentInstanceProvider
import com.composetest.core.network.di.qualifier.ApiQualifier
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.weatherforecast.data.datasource.OpenWeatherDataSource
import com.composetest.feature.weatherforecast.data.datasource.local.OpenWeatherFakeDataSourceImpl
import com.composetest.feature.weatherforecast.data.datasource.remote.OpenWeatherDataSourceImpl
import com.composetest.feature.weatherforecast.network.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Provides
    fun openWeatherDataSource(
        environmentInstanceProvider: EnvironmentInstanceProvider,
        apiCallUtils: ApiCallUtils,
        assetsProvider: AssetsProvider,
        @ApiQualifier(Api.OPEN_WEATHER_API) openWeatherApi: HttpClient
    ): OpenWeatherDataSource = environmentInstanceProvider.getInstance(
        instance = OpenWeatherDataSourceImpl(
            apiCallUtils = apiCallUtils,
            openWeatherApi = openWeatherApi,
        ),
        fakeInstance = OpenWeatherFakeDataSourceImpl(
            apiCallUtils = apiCallUtils,
            assetsProvider = assetsProvider
        )
    )
}