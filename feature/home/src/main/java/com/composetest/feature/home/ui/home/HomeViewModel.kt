package com.composetest.feature.home.ui.home

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.analytic.events.home.HomeScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    override val analyticSender: AnalyticSender,
) : BaseViewModel() {

    override val analyticScreen = HomeScreenAnalytic

    init {
        openScreenAnalytic()
    }
}