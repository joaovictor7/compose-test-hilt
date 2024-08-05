package com.composetest.common.di.qualifiers

import com.composetest.common.enums.Dispatchers
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Dispatcher(val dispatcher: Dispatchers)