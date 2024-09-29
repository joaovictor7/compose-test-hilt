package com.composetest.feature.profile.ui.editprofile

import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.profile.models.ProfileFormModel

internal sealed interface EditProfileCommand : Command<EditProfileCommandReceiver> {
    data class SetFormData(private val profileFormModel: ProfileFormModel) : EditProfileCommand {
        override fun execute(commandReceiver: EditProfileCommandReceiver) {
            commandReceiver.setFormData(profileFormModel)
        }
    }

    data object SaveProfile : EditProfileCommand {
        override fun execute(commandReceiver: EditProfileCommandReceiver) {
            commandReceiver.saveProfile()
        }
    }
}