package com.composetest.core.ui.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AsyncTaskUtilsQualifier(val screensAnalytic: String)