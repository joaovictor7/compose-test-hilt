package com.composetest.feature.login.analytics.login

import com.composetest.core.domain.models.analytics.ClickAnalyticEvent

internal sealed class LoginClickEventAnalytic(
    clickEvent: String
) : ClickAnalyticEvent(clickEvent, LoginScreenAnalytic) {
    data object LoginButton : LoginClickEventAnalytic("login_button")
}