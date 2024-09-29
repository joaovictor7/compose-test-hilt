package com.composetest.feature.profile.ui.profile.analytics

import com.composetest.common.analytics.ClickAnalyticEvent

internal object ProfileClickEventAnalytic : ClickAnalyticEvent(
    clickEvent = "login_button",
    analyticScreen = ProfileScreenAnalytic
)
