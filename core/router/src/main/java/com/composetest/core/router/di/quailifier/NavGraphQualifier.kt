package com.composetest.core.router.di.quailifier

import com.composetest.core.router.enums.ModuleNavGraph
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavGraphQualifier(val navGraph: ModuleNavGraph)