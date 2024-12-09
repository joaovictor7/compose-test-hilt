package com.composetest.core.data.di.qualifiers

import com.composetest.core.data.enums.Api
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class ApiQualifier(val networkApi: Api)