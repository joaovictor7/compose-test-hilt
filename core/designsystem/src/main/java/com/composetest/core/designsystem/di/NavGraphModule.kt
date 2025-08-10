package com.composetest.core.designsystem.di

import com.composetest.core.designsystem.navigation.NavGraphImpl
import com.composetest.core.router.interfaces.NavGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavGraphModule {

    @Binds
    @IntoSet
    abstract fun navGraph(navGraphImpl: NavGraphImpl): NavGraph
}