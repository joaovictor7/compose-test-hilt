package com.composetest.navigation.di

import com.composetest.core.router.interfaces.NavGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NavGraphListModule {

    @Provides
    fun provideNavGraphList(
        navGraphs: Set<@JvmSuppressWildcards NavGraph>
    ): Array<NavGraph> = navGraphs.toTypedArray()
}