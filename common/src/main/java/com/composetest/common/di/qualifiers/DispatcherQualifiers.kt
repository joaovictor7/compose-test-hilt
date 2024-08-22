package com.composetest.common.di.qualifiers

import com.composetest.common.enums.Dispatcher
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherQualifier(val dispatcher: Dispatcher)