package com.composetest.feature.profile.ui.profile

import com.composetest.core.ui.interfaces.Command

internal sealed interface ProfileCommand : Command<ProfileCommandReceiver> {

    data object NavigateToEditProfile : ProfileCommand {
        override fun execute(commandReceiver: ProfileCommandReceiver) {
            commandReceiver.navigateToEditProfile()
        }
    }
}