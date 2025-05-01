package com.composetest.core.ui.di.qualifiers

import com.composetest.core.analytic.enums.ScreensAnalytic
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AsyncTaskUtilsQualifier(val screensAnalytic: ScreensAnalytic)