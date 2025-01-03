package com.composetest.feature.login.analytics.login

import com.composetest.core.domain.analytics.AnalyticEvent
import com.composetest.core.domain.analytics.AnalyticScreen
import com.composetest.core.domain.analytics.CommonAnalyticEvent

internal sealed class LoginClickEventAnalytic(
    event: String,
) : CommonAnalyticEvent.CLickButton(LoginScreenAnalytic, event) {
    data object LoginButton : LoginClickEventAnalytic("login_button")
}

sealed class LoginEventAnalytic : AnalyticEvent, AnalyticScreen by LoginScreenAnalytic {
    data class LoginSuccessful(private val successful: Boolean) : LoginEventAnalytic() {
        override val tag = "user_login"
        override val params: Map<String, Any> = mapOf(
            "successful" to successful
        )
    }
}