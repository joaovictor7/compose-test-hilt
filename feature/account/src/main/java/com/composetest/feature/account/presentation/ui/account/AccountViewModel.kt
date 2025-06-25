package com.composetest.feature.account.presentation.ui.account

import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.event.CommonAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.designsystem.enums.button.LoadingButtonState
import com.composetest.core.domain.model.UserModel
import com.composetest.core.domain.usecase.user.GetCurrentUserUseCase
import com.composetest.core.router.extension.dialogErrorDestination
import com.composetest.core.router.result.account.AccountUpdateResult
import com.composetest.core.security.provider.CipherProvider
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.extension.uiStateValue
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.provider.StringResourceProvider
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.account.analytic.screens.AccountScreenAnalytic
import com.composetest.feature.account.domain.UpdateUserUseCase
import com.composetest.feature.account.presentation.enums.AccountDataRow
import com.composetest.feature.account.presentation.model.AccountScreenModel
import com.composetest.feature.profile.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class AccountViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val stringResourceProvider: StringResourceProvider,
    private val cipherProvider: CipherProvider,
    private val analyticSender: AnalyticSender,
    @param:AsyncTaskUtilsQualifier(AccountScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<AccountUiState>, UiEvent<AccountUiEvent>, AccountIntentReceiver {

    override val intentReceiver = this

    private var originalUser: UserModel? = null

    private val _uiState = MutableStateFlow(AccountUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<AccountUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        sendOpenScreenAnalytic()
        initUiState()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(AccountScreenAnalytic))
        }
    }

    override fun updateFormData(dataRowId: AccountDataRow, value: String) {
        val index = uiStateValue.accountScreenModels.indexOfFirst { it.id == dataRowId }
        val newModels = uiStateValue.accountScreenModels.toMutableList()
        newModels[index] = uiStateValue.accountScreenModels[index].copy(text = value)
        _uiState.update { it.setAccountScreenModels(newModels) }
    }

    override fun saveData() {
        val email = uiStateValue.accountScreenModels.find { it.id == AccountDataRow.EMAIL }?.text
        val name =
            uiStateValue.accountScreenModels.find { it.id == AccountDataRow.USERNAME }?.text
        val password =
            uiStateValue.accountScreenModels.find { it.id == AccountDataRow.PASSWORD }?.text
        val userModel = UserModel(
            id = originalUser?.id.orEmpty(),
            email = email ?: originalUser?.email.orEmpty(),
            name = name ?: originalUser?.name,
            encryptedPassword = password?.let { cipherProvider.encrypt(it) }
                ?: originalUser?.encryptedPassword.orEmpty()
        )
        _uiState.update { it.setLoadingState(LoadingButtonState.LOADING) }
        asyncTaskUtils.runAsyncTask(
            coroutineScope = viewModelScope,
            onError = ::handleUpdateAccountError,
        ) {
            updateUserUseCase(userModel)
            _uiState.update { it.setLoadingState(LoadingButtonState.SUCCESS) }
        }
    }

    override fun backHandler() {
        _uiEvent.emitEvent(AccountUiEvent.NavigateBack(AccountUpdateResult))
    }

    private fun initUiState() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            originalUser = getCurrentUserUseCase()
            originalUser?.let { userModel ->
                val data = getModelsToScreen(userModel)
                _uiState.update { it.setAccountScreenModels(data) }
            }
        }
    }

    private fun getModelsToScreen(userModel: UserModel) = listOf(
        AccountScreenModel(
            id = AccountDataRow.USERNAME,
            labelTextId = R.string.account_name_title,
            text = userModel.name.orEmpty(),
            placeholder = if (userModel.name.isNullOrBlank()) stringResourceProvider.getString(R.string.account_name_not_setted) else null
        ),
        AccountScreenModel(
            id = AccountDataRow.EMAIL,
            labelTextId = R.string.account_email_title,
            text = userModel.email,
            keyboardType = KeyboardType.Email,
        ),
        AccountScreenModel(
            id = AccountDataRow.PASSWORD,
            labelTextId = R.string.account_change_password,
            keyboardType = KeyboardType.Password,
        )
    )

    private fun handleUpdateAccountError(error: Throwable) {
        _uiEvent.emitEvent(AccountUiEvent.NavigateTo(error.dialogErrorDestination()))
        _uiState.update { it.setLoadingState(LoadingButtonState.IDLE) }
    }
}