package com.composetest.feature.profile.ui.editprofile

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.profile.EditProfileScreenAnalytic
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.usecases.user.GetUserUseCase
import com.composetest.core.domain.usecases.user.UpdateUserUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiState
import com.composetest.feature.profile.mappers.ProfileFormMapper
import com.composetest.feature.profile.models.ProfileFormModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class EditProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val profileFormMapper: ProfileFormMapper,
    override val analyticSender: AnalyticSender,
) : BaseViewModel(), UiState<EditProfileUiState>, EditProfileCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = EditProfileScreenAnalytic

    private var userModel: UserModel? = null

    private val _uiState = MutableStateFlow(EditProfileUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        openScreenAnalytic()
        initUiState()
    }

    override fun setFormData(profileFormModel: ProfileFormModel) {
        profileFormModel.email?.let {
            userModel = userModel?.copy(email = it)
        }
        profileFormModel.name?.let {
            userModel = userModel?.copy(name = it)
        }
        _uiState.update {
            it.copy(profileForm = profileFormMapper.mapperToModel(userModel))
        }
    }

    override fun saveProfile() {

    }

    private fun initUiState() {
        runAsyncTask {
            getUserUseCase()?.let { userModel ->
                _uiState.update {
                    it.copy(profileForm = profileFormMapper.mapperToModel(userModel))
                }
            }
        }
    }
}