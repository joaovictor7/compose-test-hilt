package com.composetest.feature.root.navigation.di

import com.composetest.core.router.di.quailifier.NavGraphListQualifier
import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.enums.ModuleNavGraph
import com.composetest.core.router.interfaces.NavGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NavGraphListModule {

    const val ROOT_NAV_GRAPH_LIST = "ROOT_NAV_GRAPH_LIST"

    @Provides
    @NavGraphListQualifier(ROOT_NAV_GRAPH_LIST)
    fun navGraphList(
        @NavGraphQualifier(ModuleNavGraph.HOME_ROOT_FEATURE) rootHomeNavGraph: NavGraph,
        @NavGraphQualifier(ModuleNavGraph.CONFIGURATION_ROOT_FEATURE) rootConfigurationNavGraph: NavGraph,
    ): Array<NavGraph> = arrayOf(
        rootHomeNavGraph,
        rootConfigurationNavGraph,
    )
}