package com.composetest.feature.login.analytics.login

import com.composetest.core.domain.models.analytics.ClickAnalyticEvent

internal object LoginClickEventAnalytic : ClickAnalyticEvent(
    clickEvent = "login_button",
    analyticScreen = LoginScreenAnalytic
)
