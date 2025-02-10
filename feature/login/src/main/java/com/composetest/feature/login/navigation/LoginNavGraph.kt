package com.composetest.feature.login.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.ui.extensions.buildComposable
import com.composetest.feature.login.ui.login.LoginCommandReceiver
import com.composetest.feature.login.ui.login.LoginScreen
import com.composetest.feature.login.ui.login.LoginUiEvent
import com.composetest.feature.login.ui.login.LoginUiState
import com.composetest.feature.login.ui.login.LoginViewModel

object LoginNavGraph : NavGraph {
    override fun NavGraphBuilder.navGraph(navigateBackHandler: Boolean) {
        buildComposable<LoginDestination, LoginViewModel, LoginUiState, LoginUiEvent, LoginCommandReceiver>(
            screen = LoginScreen,
            navigateBackHandler = navigateBackHandler
        )
    }
}