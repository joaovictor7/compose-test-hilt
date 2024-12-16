package com.composetest.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.profile.EditProfileDestination
import com.composetest.core.router.destinations.profile.ProfileDestination
import com.composetest.core.ui.extensions.buildComposable
import com.composetest.feature.profile.ui.editprofile.EditProfileCommandReceiver
import com.composetest.feature.profile.ui.editprofile.EditProfileScreen
import com.composetest.feature.profile.ui.editprofile.EditProfileUiEvent
import com.composetest.feature.profile.ui.editprofile.EditProfileUiState
import com.composetest.feature.profile.ui.editprofile.EditProfileViewModel
import com.composetest.feature.profile.ui.profile.ProfileCommandReceiver
import com.composetest.feature.profile.ui.profile.ProfileScreen
import com.composetest.feature.profile.ui.profile.ProfileUiEvent
import com.composetest.feature.profile.ui.profile.ProfileUiState
import com.composetest.feature.profile.ui.profile.ProfileViewModel

fun NavGraphBuilder.profileNavGraph() {
    buildComposable<ProfileDestination, ProfileViewModel, ProfileUiState, ProfileUiEvent, ProfileCommandReceiver>(
        screen = ProfileScreen,
        navigateBackHandler = false
    )
    buildComposable<EditProfileDestination, EditProfileViewModel, EditProfileUiState, EditProfileUiEvent, EditProfileCommandReceiver>(
        screen = EditProfileScreen
    )
}