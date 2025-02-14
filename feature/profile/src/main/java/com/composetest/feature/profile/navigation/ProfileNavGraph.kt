package com.composetest.feature.profile.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destinations.profile.EditProfileDestination
import com.composetest.core.router.destinations.profile.ProfileDestination
import com.composetest.feature.profile.ui.editprofile.EditProfileScreen
import com.composetest.feature.profile.ui.editprofile.EditProfileViewModel
import com.composetest.feature.profile.ui.profile.ProfileScreen
import com.composetest.feature.profile.ui.profile.ProfileViewModel

fun NavGraphBuilder.profileNavGraphs(navController: NavHostController) {
    composable<ProfileDestination> {
        val viewModel = hiltViewModel<ProfileViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ProfileScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            onExecuteCommand = viewModel::executeCommand,
            navController = navController
        )
    }
    composable<EditProfileDestination> {
        val viewModel = hiltViewModel<EditProfileViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        EditProfileScreen(
            uiState = uiState,
            onExecuteCommand = viewModel::executeCommand,
        )
    }
}