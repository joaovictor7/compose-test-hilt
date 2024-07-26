package com.composetest.core.router.di

import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.managers.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class ManagerModule {

    @Binds
    @ViewModelScoped
    abstract fun navigationManager(navigationManagerImpl: NavigationManagerImpl): NavigationManager
}