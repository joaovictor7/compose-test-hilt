package com.composetest.core.data.di

import com.composetest.core.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.datasources.remote.AuthenticationDataSourceImpl
import com.composetest.core.data.datasources.remote.AuthenticationFakeDataSourceImpl
import com.composetest.core.data.mappers.AuthenticationMapper
import com.composetest.core.data.providers.EnvironmentInstanceProvider
import com.composetest.core.data.utils.RemoteCallUtils
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
        firebaseAuth: FirebaseAuth,
        authenticationMapper: AuthenticationMapper,
        remoteCallUtils: RemoteCallUtils
    ): AuthenticationDataSource = environmentInstanceProvider.getInstance(
        instance = AuthenticationDataSourceImpl(
            firebaseAuth = firebaseAuth,
            authenticationMapper = authenticationMapper,
            remoteCallUtils = remoteCallUtils
        ),
        fakeInstance = AuthenticationFakeDataSourceImpl(
            remoteCallUtils = remoteCallUtils
        )
    )
}