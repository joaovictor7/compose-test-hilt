package com.composetest.feature.login.data.di

import com.composetest.common.provider.DateTimeProvider
import com.composetest.core.data.provider.AssetsProvider
import com.composetest.core.data.provider.EnvironmentInstanceProvider
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.login.data.datasource.AuthenticationDataSource
import com.composetest.feature.login.data.datasource.local.AuthenticationFakeDataSourceImpl
import com.composetest.feature.login.data.datasource.remote.AuthenticationDataSourceImpl
import com.composetest.feature.login.data.mapper.AuthenticationMapper
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Provides
    fun authenticationDataSource(
        environmentInstanceProvider: EnvironmentInstanceProvider,
        dateTimeProvider: DateTimeProvider,
        assetsProvider: AssetsProvider,
        firebaseAuth: FirebaseAuth,
        authenticationMapper: AuthenticationMapper,
        apiCallUtils: ApiCallUtils,
    ): AuthenticationDataSource = environmentInstanceProvider.getInstance(
        instance = AuthenticationDataSourceImpl(
            firebaseAuth = firebaseAuth,
            authenticationMapper = authenticationMapper,
            apiCallUtils = apiCallUtils
        ),
        fakeInstance = AuthenticationFakeDataSourceImpl(
            apiCallUtils = apiCallUtils,
            dateTimeProvider = dateTimeProvider,
            assetsProvider = assetsProvider
        )
    )
}