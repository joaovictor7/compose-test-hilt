package com.composetest.feature.login.extensions

import com.composetest.core.router.destinations.login.LoginDestination

internal val LoginDestination.autoShowBiometricPrompt get() = !isLogout && !expiredSession