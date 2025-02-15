package com.composetest.feature.home.ui.home

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.analytics.home.HomeScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
) : BaseViewModel() {

    override val analyticScreen = HomeScreenAnalytic

    init {
        openScreenAnalytic()
    }
}