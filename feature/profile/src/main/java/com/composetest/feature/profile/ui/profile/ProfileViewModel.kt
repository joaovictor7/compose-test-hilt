package com.composetest.feature.profile.ui.profile

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.usecases.user.GetUserUseCase
import com.composetest.core.router.destinations.profile.EditProfileDestination
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.providers.StringResourceProvider
import com.composetest.core.ui.utils.AsyncTaskUtils
import com.composetest.feature.profile.R
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
    private val analyticSender: AnalyticSender,
    @AsyncTaskUtilsQualifier(ScreensAnalytic.PROFILE) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<ProfileUiState>, UiEvent<ProfileUiEvent>, ProfileCommandReceiver {

    override val commandReceiver = this

    private val _uiState = MutableStateFlow(ProfileUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ProfileUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        sendOpenScreenAnalytic()
        initUiState()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ScreensAnalytic.PROFILE))
        }
    }

    override fun toolbarActionCLick(action: TopBarAction) {
        if (action == TopBarAction.EDIT) {
            _uiEvent.emitEvent(ProfileUiEvent.NavigateTo(NavigationModel(EditProfileDestination)))
        }
    }

    private fun initUiState() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
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