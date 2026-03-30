package com.composetest.feature.home.presenter.ui.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.home.analytic.screen.HomeScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val analyticSender: AnalyticSender,
    private val coroutineContext: CoroutineContext,
    @param:AsyncTaskUtilsQualifier(HomeScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel() {

    init {
        sendOpenScreenAnalytic()
    }

    override fun sendOpenScreenAnalytic() {
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runAsyncTask {
                analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(HomeScreenAnalytic))
            }
        }
    }
}