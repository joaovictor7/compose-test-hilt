package com.composetest.feature.login.presenter.extensions

import com.composetest.core.router.destinations.login.LoginDestination

internal val LoginDestination.autoShowBiometricPrompt get() = !isLogout && !expiredSession