package com.composetest.feature.login.analytic.event

import com.composetest.core.analytic.api.event.AnalyticEvent
import com.composetest.core.analytic.api.screen.ScreenAnalytic
import com.composetest.feature.login.analytic.screen.LoginScreenAnalytic

internal sealed class LoginEventAnalytic : AnalyticEvent, ScreenAnalytic by LoginScreenAnalytic {
    data class LoginSuccessful(private val successful: Boolean) : LoginEventAnalytic() {
        override val tag = "user_login"
        override val params: Map<String, Any> = mapOf("successful" to successful)
    }
}