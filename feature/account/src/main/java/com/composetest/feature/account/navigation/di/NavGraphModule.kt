package com.composetest.feature.account.navigation.di

import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.enums.ModuleNavGraph
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.account.navigation.NavGraphImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavGraphModule {

    @Binds
    @NavGraphQualifier(ModuleNavGraph.ACCOUNT_FEATURE)
    abstract fun navGraph(navGraphImpl: NavGraphImpl): NavGraph
}