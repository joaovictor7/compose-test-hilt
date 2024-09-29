package com.composetest.feature.profile.ui.profile

import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.profile.models.ProfileFormModel

internal sealed interface ProfileCommand : Command<ProfileCommandReceiver> {
    data class SetFormData(private val profileFormModel: ProfileFormModel) : ProfileCommand {
        override fun execute(commandReceiver: ProfileCommandReceiver) {
            commandReceiver.setFormData(profileFormModel)
        }
    }

    data object SaveProfile : ProfileCommand {
        override fun execute(commandReceiver: ProfileCommandReceiver) {
            commandReceiver.saveProfile()
        }
    }
}