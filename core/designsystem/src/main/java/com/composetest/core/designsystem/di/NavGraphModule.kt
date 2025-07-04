package com.composetest.core.designsystem.di

import com.composetest.core.designsystem.navigation.NavGraphImpl
import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.enums.ModuleNavGraph
import com.composetest.core.router.interfaces.NavGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavGraphModule {

    @Binds
    @NavGraphQualifier(ModuleNavGraph.DESIGN_SYSTEM_NAV_GRAPH)
    abstract fun navGraph(navGraphImpl: NavGraphImpl): NavGraph
}