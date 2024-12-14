package com.composetest.feature.profile.ui.profile

import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface ProfileCommandReceiver : CommandReceiver<ProfileCommandReceiver> {

    fun toolbarActionCLick(action: TopBarAction)
}