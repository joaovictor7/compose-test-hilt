package com.composetest.feature.profile.analytics.editprofile

import com.composetest.core.domain.models.analytics.ClickAnalyticEvent
import com.composetest.feature.profile.analytics.profile.ProfileScreenAnalytic

internal object EditProfileClickEventAnalytic : ClickAnalyticEvent(
    clickEvent = "login_button",
    analyticScreen = ProfileScreenAnalytic
)
