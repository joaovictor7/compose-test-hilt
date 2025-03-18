package com.composetest.core.ui.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AsyncTaskUtilsQualifier(val screenAnalytic: String)