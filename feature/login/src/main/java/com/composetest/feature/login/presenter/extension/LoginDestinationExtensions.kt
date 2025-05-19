package com.composetest.feature.login.presenter.extension

import com.composetest.core.router.destination.login.LoginDestination

internal val LoginDestination.autoShowBiometricPrompt get() = !isLogout && !expiredSession