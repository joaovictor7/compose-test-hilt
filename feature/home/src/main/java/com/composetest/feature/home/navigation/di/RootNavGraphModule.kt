package com.composetest.feature.home.navigation.di

import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.enums.ModuleNavGraph
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.home.navigation.RootNavGraphImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RootNavGraphModule {

    @Binds
    @IntoSet
    @NavGraphQualifier(ModuleNavGraph.ROOT_FEATURE)
    abstract fun navGraph(rootNavGraphImpl: RootNavGraphImpl): NavGraph
}