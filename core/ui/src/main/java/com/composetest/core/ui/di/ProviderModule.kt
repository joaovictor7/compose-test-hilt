package com.composetest.core.ui.di

import com.composetest.core.ui.providers.LocationProvider
import com.composetest.core.ui.providers.LocationProviderImpl
import com.composetest.core.ui.providers.PermissionProvider
import com.composetest.core.ui.providers.PermissionProviderImpl
import com.composetest.core.ui.providers.StringResourceProvider
import com.composetest.core.ui.providers.StringResourceProviderImpl
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