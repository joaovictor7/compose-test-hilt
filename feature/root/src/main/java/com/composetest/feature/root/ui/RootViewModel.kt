package com.composetest.feature.root.ui

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.root.ui.analytics.RootAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class RootViewModel @Inject constructor(
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<RootUiState>(RootAnalytic, RootUiState()), RootCommandReceiver {

    override val commandReceiver = this
}