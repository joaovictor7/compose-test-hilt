package com.composetest.core.analytic.events.login

import com.composetest.core.analytic.AnalyticEvent
import com.composetest.core.analytic.AnalyticScreen
import com.composetest.core.analytic.enums.ScreensAnalytic

sealed class LoginEventAnalytic : AnalyticEvent, AnalyticScreen by ScreensAnalytic.LOGIN {
    data class LoginSuccessful(private val successful: Boolean) : LoginEventAnalytic() {
        override val tag = "user_login"
        override val params: Map<String, Any> = mapOf("successful" to successful)
    }
}