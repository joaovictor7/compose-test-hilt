package com.composetest.feature.root.navigation.di

import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.configuration.navigation.RootConfigurationNavGraph
import com.composetest.feature.home.navigation.RootHomeNavGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class NavigationModule {

    @Provides
    @NavGraphQualifier(ROOT_NAV_GRAPH)
    fun teste(): Array<NavGraph> = arrayOf(
        RootHomeNavGraph,
        RootConfigurationNavGraph,
    )

    companion object {
        const val ROOT_NAV_GRAPH = "root_nav_graph"
    }
}