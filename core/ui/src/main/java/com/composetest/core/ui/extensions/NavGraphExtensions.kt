package com.composetest.core.ui.extensions

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
import androidx.navigation.compose.composable
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.router.utils.getNavTypes
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.core.ui.interfaces.Screen

inline fun <reified D, reified VM, US, CR> NavGraphBuilder.composable(
    screen: Screen<US, CR>,
    navigateBackHandler: Boolean = true,
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    noinline popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
) where D : Destination, VM : BaseViewModel<US>, VM : CommandReceiver<CR>, US : BaseUiState, CR : CommandReceiver<CR> {
    composable<D>(
        typeMap = getNavTypes<D>(),
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        val viewModel = hiltViewModel<VM>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        if (navigateBackHandler) {
            BackHandler {
                viewModel.navigateBack()
            }
        }
        screen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
}