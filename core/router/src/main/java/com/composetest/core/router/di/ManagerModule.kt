package com.composetest.core.router.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.common.di.qualifiers.DispatcherQualifier
import com.composetest.common.enums.Dispatcher
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.managers.NavigationManagerImpl
import com.composetest.core.router.providers.NavControllerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
internal object ManagerModule {

    @Provides
    @ViewModelScoped
    @NavGraphQualifier(NavGraph.MAIN)
    fun navigationMainManager(
        navControllerProvider: NavControllerProvider,
        savedStateHandle: SavedStateHandle,
        @DispatcherQualifier(Dispatcher.Main) mainDispatcherQualifier: CoroutineDispatcher
    ): NavigationManager = NavigationManagerImpl(
        navControllerProvider = navControllerProvider,
        navGraph = NavGraph.MAIN,
        mainDispatcher = mainDispatcherQualifier,
        savedStateHandle = savedStateHandle
    )

    @Provides
    @ViewModelScoped
    @NavGraphQualifier(NavGraph.ROOT)
    fun navigationRootManager(
        navControllerProvider: NavControllerProvider,
        savedStateHandle: SavedStateHandle,
        @DispatcherQualifier(Dispatcher.Main) mainDispatcherQualifier: CoroutineDispatcher
    ): NavigationManager = NavigationManagerImpl(
        navControllerProvider = navControllerProvider,
        navGraph = NavGraph.ROOT,
        mainDispatcher = mainDispatcherQualifier,
        savedStateHandle = savedStateHandle
    )
}