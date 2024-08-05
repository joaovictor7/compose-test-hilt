package com.composetest.core.data.di.qualifiers

import com.composetest.core.data.enums.NetworkApi
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class Api(val networkApi: NetworkApi)