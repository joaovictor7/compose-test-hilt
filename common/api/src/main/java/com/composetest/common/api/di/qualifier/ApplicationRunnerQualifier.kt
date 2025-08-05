package com.composetest.common.api.di.qualifier

import com.composetest.common.api.enums.ApplicationModule
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationRunnerQualifier(val applicationModule: ApplicationModule)