package com.composetest.feature.login.presentation.extension

import com.composetest.core.router.navkey.login.LoginNavKey

internal val LoginNavKey.autoShowBiometricPrompt get() = !isLogout && !expiredSession