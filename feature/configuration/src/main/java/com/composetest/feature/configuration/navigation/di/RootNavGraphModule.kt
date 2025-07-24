package com.composetest.feature.configuration.navigation.di

import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.enums.ModuleNavGraph
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.configuration.navigation.RootNavGraphImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RootNavGraphModule {

    @Binds
    @NavGraphQualifier(ModuleNavGraph.CONFIGURATION_ROOT_FEATURE)
    abstract fun navGraph(rootNavGraphImpl: RootNavGraphImpl): NavGraph
}