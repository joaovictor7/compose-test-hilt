package com.composetest.core.designsystem.navigation.di

import com.composetest.core.designsystem.navigation.NavGraphImpl
import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navgraph.MainNavGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavGraphModule {

    @Binds
    @NavGraphQualifier(MainNavGraph.DESIGN_SYSTEM_NAV_GRAPH)
    abstract fun navGraph(navGraphImpl: NavGraphImpl): NavGraph
}