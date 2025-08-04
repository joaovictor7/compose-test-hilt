package com.composetest.feature.product.network.di

import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.network.HttpClientBuilder
import com.composetest.core.network.di.qualifier.ApiQualifier
import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.network.HttpClientBuilder
import com.composetest.core.network.di.qualifier.ApiQualifier
import com.composetest.feature.product.network.ProductApiSetting
import com.composetest.feature.product.network.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @ApiQualifier(Api.DUMMY_JSON_PRODUCTS)
    fun coinApi(
        buildConfigProvider: BuildConfigProvider
    ): HttpClient = HttpClientBuilder.build(
        ProductApiSetting(
            url = buildConfigProvider.buildConfigFields.dummyJsonHost,
        )
    )
}