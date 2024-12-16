package com.composetest.core.router.managers

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.composetest.common.providers.DispatcherProvider
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.router.interfaces.ResultParam
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

internal class NavigationManagerImpl(
    private val navControllerManager: NavControllerManager,
    private val navGraph: NavGraph,
    private val dispatcherProvider: DispatcherProvider
) : NavigationManager {

    private val navController get() = navControllerManager.getNavController(navGraph)
    private val navigateBackAvailable
        get() = navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

    override val currentRoute get() = navController.currentDestination?.route
    override val currentRouteChangesFlow
        get() = navController.currentBackStackEntryFlow
            .map { it.destination.route }
            .flowOn(dispatcherProvider.io)

    override fun <D : Destination> navigate(
        destination: D,
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

    override suspend fun <D : Destination> asyncNavigate(
        destination: D,
        navigationMode: NavigationMode?
    ) = withContext(dispatcherProvider.main) {
        navigate(destination, navigationMode)
    }

    override suspend fun asyncNavigateBack() = withContext(dispatcherProvider.main) {
        navigateBack()
    }

    override suspend fun <Result : ResultParam> asyncNavigateBack(result: Result) =
        withContext(dispatcherProvider.main) {
            navigateBack(result)
        }

    override fun <Result : ResultParam> getResultFlow(resultClass: KClass<Result>) =
        navController.currentBackStackEntryFlow.transform {
            with(it.savedStateHandle) {
                val key = resultClass.simpleName.orEmpty()
                get<Result>(key)?.let { result ->
                    emit(result)
                    remove<Result>(key)
                }
            }
        }.flowOn(dispatcherProvider.main)

    private fun getNavigateOptions(mode: NavigationMode?) = NavOptions.Builder().apply {
        when (mode) {
            NavigationMode.REMOVE_CURRENT_SCREEN -> navController.currentDestination?.id?.let {
                setPopUpTo(destinationId = it, inclusive = true)
            }

            NavigationMode.REMOVE_ALL_SCREENS_STACK -> {
                setPopUpTo(destinationId = 0, inclusive = true)
            }

            NavigationMode.SAVE_SCREEN_STATE -> runCatching {
                setPopUpTo(
                    destinationId = navController.graph.findStartDestination().id,
                    inclusive = false,
                    saveState = true
                )
                setRestoreState(true)
            }

            else -> Unit
        }
        setLaunchSingleTop(true)
    }.build()
}