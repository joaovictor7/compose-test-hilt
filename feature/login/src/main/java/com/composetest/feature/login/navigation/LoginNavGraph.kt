package com.composetest.feature.login.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.extensions.composable
import com.composetest.feature.login.ui.login.LoginCommandReceiver
import com.composetest.feature.login.ui.login.LoginScreen
import com.composetest.feature.login.ui.login.LoginUiState
import com.composetest.feature.login.ui.login.LoginViewModel

fun NavGraphBuilder.loginNavGraph() {
    composable<LoginDestination, LoginViewModel, LoginUiState, LoginCommandReceiver>(LoginScreen)
}