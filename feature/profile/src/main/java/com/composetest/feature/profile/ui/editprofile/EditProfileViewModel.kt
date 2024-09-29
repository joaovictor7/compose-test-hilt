package com.composetest.feature.profile.ui.editprofile

import androidx.lifecycle.viewModelScope
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.usecases.GetUserUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.domain.usecases.UpdateUserUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.profile.mappers.ProfileFormMapper
import com.composetest.feature.profile.models.ProfileFormModel
import com.composetest.feature.profile.ui.profile.analytics.ProfileScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EditProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val profileFormMapper: ProfileFormMapper,
    @NavGraphQualifier(NavGraph.MAIN) private val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<EditProfileUiState>(ProfileScreenAnalytic, EditProfileUiState()), EditProfileCommandReceiver {

    private var userModel: UserModel? = null

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        initState()
    }

    override fun setFormData(profileFormModel: ProfileFormModel) {
        profileFormModel.email?.let {
            userModel = userModel?.copy(email = it)
        }
        profileFormModel.name?.let {
            userModel = userModel?.copy(name = it)
        }
        updateUiState {
            it.copy(profileForm = profileFormMapper(userModel))
        }
    }

    override fun saveProfile() {

    }

    private fun initState() {
        viewModelScope.launch {
            userModel = getUserUseCase()
            userModel?.let { t ->
                updateUiState {
                    it.copy(profileForm = profileFormMapper(userModel))
                }
            }
        }
    }
}