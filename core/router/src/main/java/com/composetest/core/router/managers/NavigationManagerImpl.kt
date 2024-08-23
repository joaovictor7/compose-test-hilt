package com.composetest.core.router.managers

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.ResultParam
import com.composetest.core.router.providers.NavControllerProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

internal class NavigationManagerImpl(
    private val navControllerProvider: NavControllerProvider,
    private val navGraph: NavGraph,
    private val mainDispatcher: CoroutineDispatcher,
    override val savedStateHandle: SavedStateHandle
) : NavigationManager {

    private val navController get() = navControllerProvider.getNavController(navGraph)
    private val navigateBackAvailable
        get() = navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED
    private val navBackStackEntryFlow: Flow<NavBackStackEntry>
        get() = navController.currentBackStackEntryFlow

    override fun <Destination : Any> navigate(
        destination: Destination,
        navigationMode: NavigationMode?
    ) {
        navController.navigate(
            route = destination,
            navOptions = getNavigateOptions(navigationMode)
        )
    }

    override fun navigateBack() {
        if (navigateBackAvailable) navController.popBackStack()
    }

    override fun <Result : ResultParam> navigateBack(result: Result) {
        if (!navigateBackAvailable) return
        navController.previousBackStackEntry?.savedStateHandle?.set(
            result::class.simpleName.orEmpty(),
            result
        )
        navController.popBackStack()
    }

    override fun <Result : ResultParam> getResultFlow(resultClass: KClass<Result>) =
        navBackStackEntryFlow.transform {
            with(it.savedStateHandle) {
                val key = resultClass.simpleName.orEmpty()
                get<Result>(key)?.let { result ->
                    emit(result)
                    remove<Result>(key)
                }
            }
        }.flowOn(mainDispatcher)

    override fun isCurrentScreen(destination: Any): Boolean {
        return navController == navController.currentDestination
    }

    override suspend fun <Destination : Any> asyncNavigate(
        destination: Destination,
        navigationMode: NavigationMode?
    ) = withContext(mainDispatcher) {
        navigate(destination, navigationMode)
    }

    override suspend fun asyncNavigateBack() = withContext(mainDispatcher) {
        navigateBack()
    }

    override suspend fun <Result : ResultParam> asyncNavigateBack(result: Result) =
        withContext(mainDispatcher) {
            navigateBack(result)
        }

    private fun getNavigateOptions(mode: NavigationMode?) = NavOptions.Builder().apply {
        mode?.let { mode ->
            when (mode) {
                NavigationMode.REMOVE_CURRENT_SCREEN -> navController.currentDestination?.id?.let {
                    setPopUpTo(destinationId = it, inclusive = true)
                }
                NavigationMode.REMOVE_ALL_SCREENS_STACK -> {
                    setPopUpTo(destinationId = 0, inclusive = true)
                }
                NavigationMode.NESTED_NAVIGATION -> {
                    setPopUpTo(
                        destinationId = navController.graph.findStartDestination().id,
                        inclusive = false,
                        saveState = true
                    )
                    setRestoreState(true)
                }
            }
        }
        setLaunchSingleTop(true)
    }.build()
}