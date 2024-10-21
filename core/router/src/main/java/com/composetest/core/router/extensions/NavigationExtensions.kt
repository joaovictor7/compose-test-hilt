package com.composetest.core.router.extensions

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.core.ui.interfaces.Screen
import kotlin.reflect.KType
import kotlin.reflect.full.companionObjectInstance
import com.composetest.core.router.interfaces.NavType as NavTypes

inline fun <reified D : Destination> NavigationManager.getParam() =
    savedStateHandle.toRoute<D>(getNavTypes(D::class.companionObjectInstance))

inline fun <reified D, reified VM, US, CR> NavGraphBuilder.composable(
    screen: Screen<US, CR>,
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    noinline popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
) where D : Destination, VM : BaseViewModel<US>, VM : CommandReceiver<CR>, US : BaseUiState, CR : CommandReceiver<CR> {
    composable<D>(
        typeMap = getNavTypes(D::class.companionObjectInstance),
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        val viewModel = hiltViewModel<VM>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        screen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
}

@PublishedApi
internal fun getNavTypes(companionObject: Any?): Map<KType, NavType<*>> =
    (companionObject as? NavTypes)?.navTypes ?: emptyMap()