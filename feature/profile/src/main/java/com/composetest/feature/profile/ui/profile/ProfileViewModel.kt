package com.composetest.feature.profile.ui.profile

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import com.composetest.common.providers.StringResourceProvider
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.usecases.GetUserUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.profile.EditProfileDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.profile.R
import com.composetest.feature.profile.analytics.profile.ProfileScreenAnalytic
import com.composetest.feature.profile.models.ProfileScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val stringResourceProvider: StringResourceProvider,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<ProfileUiState, ProfileUiEvent>(ProfileScreenAnalytic, ProfileUiState()), ProfileCommandReceiver {

    override val commandReceiver = this

    override fun initUiState() {
        openScreenAnalytic()
        runAsyncTask {
            getUserUseCase()?.let { userModel ->
                val profileScreenModel = getModelToScreen(userModel)
                updateUiState { it.setProfileScreenModels(profileScreenModel) }
            }
        }
    }

    override fun navigateToEditProfile() {
        navigationManager.navigate(EditProfileDestination)
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