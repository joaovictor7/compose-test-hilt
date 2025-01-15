package com.composetest.feature.login.analytics.login

import com.composetest.common.analytics.AnalyticEvent
import com.composetest.common.analytics.AnalyticScreen

sealed class LoginEventAnalytic : AnalyticEvent, AnalyticScreen by LoginScreenAnalytic {
    data class LoginSuccessful(private val successful: Boolean) : LoginEventAnalytic() {
        override val tag = "user_login"
        override val params: Map<String, Any> = mapOf(
            "successful" to successful
        )
    }
}