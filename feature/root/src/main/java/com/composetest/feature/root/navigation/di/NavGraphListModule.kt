package com.composetest.feature.root.navigation.di

import com.composetest.core.router.di.quailifier.NavGraphListQualifier
import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navgraph.MainNavGraph
import com.composetest.core.router.navgraph.RootNavGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NavGraphListModule {

    @Provides
    @NavGraphListQualifier(MainNavGraph.ROOT_NAV_GRAPH)
    fun navGraphList(
        @NavGraphQualifier(RootNavGraph.HOME_ROOT_NAV_GRAPH) rootHomeNavGraph: NavGraph,
        @NavGraphQualifier(RootNavGraph.CONFIGURATION_ROOT_NAV_GRAPH) rootConfigurationNavGraph: NavGraph,
    ): Array<NavGraph> = arrayOf(
        rootHomeNavGraph,
        rootConfigurationNavGraph,
    )
}