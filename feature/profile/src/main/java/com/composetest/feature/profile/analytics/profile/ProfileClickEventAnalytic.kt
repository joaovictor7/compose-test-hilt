package com.composetest.feature.profile.analytics.profile

import com.composetest.core.domain.models.analytics.ClickAnalyticEvent

internal object ProfileClickEventAnalytic : ClickAnalyticEvent(
    clickEvent = "login_button",
    analyticScreen = ProfileScreenAnalytic
)
