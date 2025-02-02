package com.composetest.core.ui.di

import com.composetest.core.ui.providers.PermissionProvider
import com.composetest.core.ui.providers.PermissionProviderImpl
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
}