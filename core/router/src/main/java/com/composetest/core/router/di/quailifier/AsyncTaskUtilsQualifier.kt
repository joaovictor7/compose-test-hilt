package com.composetest.core.router.di.quailifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavGraphQualifier(val navGraph: String)