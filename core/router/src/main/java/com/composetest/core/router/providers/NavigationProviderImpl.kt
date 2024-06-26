package com.composetest.core.router.providers

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class NavigationProviderImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider,
    override val savedStateHandle: SavedStateHandle
) : NavigationProvider {

    private val navController get() = navControllerProvider.navController

    override fun <Destination : Any> navigate(
        destination: Destination,
        removeCurrentScreen: Boolean
    ) {
        navController?.run {
            navigate(
                route = destination,
                navOptions = NavOptionsBuilder().apply {
                    if (removeCurrentScreen) popUpScreen(currentDestination) else singleLauncher()
                }.build()
            )
        }
    }

    override fun <Destination : Any> navigateToBack(destination: Destination) {
        navController?.run {
            navigate(
                route = destination,
                navOptions = NavOptionsBuilder()
                    .popUpScreen(previousBackStackEntry?.destination)
                    .build()
            )
        }
    }

    override fun navigateToBack() {
        navController?.popBackStack()
    }

    private inner class NavOptionsBuilder {

        private var navOptions = NavOptions.Builder()

        fun singleLauncher() = apply {
            navOptions = navOptions.setLaunchSingleTop(true)
        }

        fun popUpScreen(navDestination: NavDestination?) = apply {
            navDestination?.id?.let { destinationId ->
                navOptions = navOptions.setPopUpTo(destinationId, true)
            }
        }

        fun build() = navOptions.build()
    }
}