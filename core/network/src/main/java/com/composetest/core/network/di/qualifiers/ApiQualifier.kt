package com.composetest.core.network.di.qualifiers

import com.composetest.core.network.enums.Api
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiQualifier(val networkApi: Api)