package com.composetest.core.router.di.qualifiers

import com.composetest.core.router.enums.NavGraph
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavGraphQualifier(val navGraph: NavGraph)