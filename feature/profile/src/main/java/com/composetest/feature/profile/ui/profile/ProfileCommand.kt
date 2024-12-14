package com.composetest.feature.profile.ui.profile

import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.ui.interfaces.Command

internal sealed interface ProfileCommand : Command<ProfileCommandReceiver> {

    data class ToolbarActionClick(private val action: TopBarAction) : ProfileCommand {
        override fun execute(commandReceiver: ProfileCommandReceiver) {
            commandReceiver.toolbarActionCLick(action)
        }
    }
}