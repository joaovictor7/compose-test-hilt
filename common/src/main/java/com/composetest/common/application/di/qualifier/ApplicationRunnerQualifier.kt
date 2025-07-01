package com.composetest.common.application.di.qualifier

import com.composetest.common.application.enums.ApplicationModule
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationRunnerQualifier(val applicationModule: ApplicationModule)