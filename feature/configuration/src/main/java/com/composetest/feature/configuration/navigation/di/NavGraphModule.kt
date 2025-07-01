package com.composetest.feature.configuration.navigation.di

import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.enums.ModuleNavGraph
import com.composetest.feature.configuration.navigation.NavGraphImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavGraphModule {

    @Binds
    @NavGraphQualifier(ModuleNavGraph.CONFIGURATION_NAV_GRAPH)
    abstract fun navGraph(navGraphImpl: NavGraphImpl): NavGraph
}