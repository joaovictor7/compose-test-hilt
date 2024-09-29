package com.composetest.feature.profile.ui.editprofile.analytics

import com.composetest.common.analytics.ClickAnalyticEvent
import com.composetest.feature.profile.ui.profile.analytics.ProfileScreenAnalytic

internal object EditProfileClickEventAnalytic : ClickAnalyticEvent(
    clickEvent = "login_button",
    analyticScreen = ProfileScreenAnalytic
)
