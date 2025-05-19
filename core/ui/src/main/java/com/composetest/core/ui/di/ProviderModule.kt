package com.composetest.core.ui.di

import com.composetest.core.ui.provider.LocationProvider
import com.composetest.core.ui.provider.LocationProviderImpl
import com.composetest.core.ui.provider.PermissionProvider
import com.composetest.core.ui.provider.PermissionProviderImpl
import com.composetest.core.ui.provider.StringResourceProvider
import com.composetest.core.ui.provider.StringResourceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    abstract fun permissionProvider(
        permissionProviderImpl: PermissionProviderImpl
    ): PermissionProvider

    @Binds
    abstract fun stringResourceProvider(
        stringResourceProviderImpl: StringResourceProviderImpl
    ): StringResourceProvider

    @Binds
    abstract fun locationProvider(
        locationProviderImpl: LocationProviderImpl
    ): LocationProvider
}