package com.composetest.feature.login.analytic.events

import com.composetest.core.analytic.event.AnalyticEvent
import com.composetest.core.analytic.screen.ScreenAnalytic
import com.composetest.feature.login.analytic.screens.LoginScreenAnalytic

internal sealed class LoginEventAnalytic : AnalyticEvent, ScreenAnalytic by LoginScreenAnalytic {
    data class LoginSuccessful(private val successful: Boolean) : LoginEventAnalytic() {
        override val tag = "user_login"
        override val params: Map<String, Any> = mapOf("successful" to successful)
    }
}