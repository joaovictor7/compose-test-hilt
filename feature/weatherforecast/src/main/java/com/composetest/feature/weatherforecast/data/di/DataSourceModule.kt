package com.composetest.feature.weatherforecast.data.di

import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.providers.EnvironmentInstanceProvider
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.core.network.di.qualifiers.ApiQualifier
import com.composetest.feature.weatherforecast.data.datasources.OpenWeatherDataSource
import com.composetest.feature.weatherforecast.data.datasources.local.OpenWeatherFakeDataSourceImpl
import com.composetest.feature.weatherforecast.data.datasources.remote.OpenWeatherDataSourceImpl
import com.composetest.feature.weatherforecast.network.constants.ApiConstants
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
        @ApiQualifier(ApiConstants.OPEN_WEATHER_API) openWeatherApi: HttpClient
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