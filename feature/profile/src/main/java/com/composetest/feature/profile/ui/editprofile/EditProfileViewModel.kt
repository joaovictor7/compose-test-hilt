package com.composetest.feature.profile.ui.editprofile

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.analytic.events.profile.EditProfileScreenAnalytic
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.usecases.user.GetUserUseCase
import com.composetest.core.domain.usecases.user.UpdateUserUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.utils.AsyncTaskUtils
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
    private val analyticSender: AnalyticSender,
    @AsyncTaskUtilsQualifier(EditProfileScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<EditProfileUiState>, EditProfileCommandReceiver {

    override val commandReceiver = this

    private var userModel: UserModel? = null

    private val _uiState = MutableStateFlow(EditProfileUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        sendOpenScreenAnalytic()
        initUiState()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(EditProfileScreenAnalytic))
        }
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
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            getUserUseCase()?.let { userModel ->
                _uiState.update {
                    it.copy(profileForm = profileFormMapper.mapperToModel(userModel))
                }
            }
        }
    }
}