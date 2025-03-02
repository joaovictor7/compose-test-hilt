package com.composetest.feature.profile.ui.profile

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import com.composetest.common.providers.StringResourceProvider
import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.domain.usecases.user.GetUserUseCase
import com.composetest.core.router.destinations.profile.EditProfileDestination
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.feature.profile.R
import com.composetest.feature.profile.analytics.profile.ProfileScreenAnalytic
import com.composetest.feature.profile.models.ProfileScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val stringResourceProvider: StringResourceProvider,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
) : BaseViewModel(), UiState<ProfileUiState>, UiEvent<ProfileUiEvent>, ProfileCommandReceiver {

    private val _uiState = MutableStateFlow(ProfileUiState())
    private val _uiEvent = MutableSharedFlow<ProfileUiEvent>()

    override val uiState = _uiState.asStateFlow()
    override val uiEvent = _uiEvent.asSharedFlow()
    override val commandReceiver = this
    override val analyticScreen = ProfileScreenAnalytic

    init {
        openScreenAnalytic()
        initUiState()
    }

    override fun toolbarActionCLick(action: TopBarAction) {
        if (action == TopBarAction.EDIT) {
            _uiEvent.emitEvent(ProfileUiEvent.NavigateTo(NavigationModel(EditProfileDestination)))
        }
    }

    private fun initUiState() {
        runAsyncTask {
            getUserUseCase()?.let { userModel ->
                val profileScreenModel = getModelToScreen(userModel)
                _uiState.update { it.setProfileScreenModels(profileScreenModel) }
            }
        }
    }

    private fun getModelToScreen(userModel: UserModel) = listOf(
        ProfileScreenModel(
            titleId = R.string.profile_email_title,
            text = AnnotatedString(userModel.email)
        ),
        ProfileScreenModel(
            titleId = R.string.profile_name_title,
            text = userModel.name?.let { AnnotatedString(it) } ?: buildAnnotatedString {
                val text = stringResourceProvider.getString(
                    R.string.profile_email_not_found,
                    R.string.profile_email_title
                )
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(text)
                }
            }
        )
    )
}