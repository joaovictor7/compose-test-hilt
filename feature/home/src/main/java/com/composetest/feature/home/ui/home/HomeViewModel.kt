package com.composetest.feature.home.ui.home

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.analytic.events.home.HomeScreenAnalytic
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.utils.AsyncTaskUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val analyticSender: AnalyticSender,
    @AsyncTaskUtilsQualifier(HomeScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel() {

    init {
        sendOpenScreenAnalytic()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(HomeScreenAnalytic))
        }
    }
}