package com.composetest.core.router.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.common.providers.DispatcherProvider
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavControllerManager
import com.composetest.core.router.managers.NavControllerManagerImpl
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.managers.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelManagerModule {

    @Provides
    @NavGraphQualifier(NavGraph.MAIN)
    fun navigationMainManager(
        navControllerManager: NavControllerManager,
        savedStateHandle: SavedStateHandle,
        dispatcherProvider: DispatcherProvider
    ): NavigationManager = NavigationManagerImpl(
        navControllerManager = navControllerManager,
        navGraph = NavGraph.MAIN,
        dispatcherProvider = dispatcherProvider,
        savedStateHandle = savedStateHandle
    )

    @Provides
    @NavGraphQualifier(NavGraph.ROOT)
    fun navigationRootManager(
        navControllerManager: NavControllerManager,
        savedStateHandle: SavedStateHandle,
        dispatcherProvider: DispatcherProvider
    ): NavigationManager = NavigationManagerImpl(
        navControllerManager = navControllerManager,
        navGraph = NavGraph.ROOT,
        dispatcherProvider = dispatcherProvider,
        savedStateHandle = savedStateHandle
    )
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SingletonManagerModule {

    @Binds
    @Singleton
    abstract fun navControllerManager(
        navControllerManagerImpl: NavControllerManagerImpl
    ): NavControllerManager
}