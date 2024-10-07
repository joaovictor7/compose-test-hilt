package com.composetest.feature.profile.ui.profile

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface ProfileCommandReceiver : CommandReceiver<ProfileCommandReceiver> {

    fun navigateToEditProfile()
}