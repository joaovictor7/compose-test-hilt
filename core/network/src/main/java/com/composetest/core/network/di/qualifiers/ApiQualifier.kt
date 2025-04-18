package com.composetest.core.network.di.qualifiers

import javax.inject.Qualifier

annotation class ApiQualifier {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NewsApi

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OpenWeather

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinApi
}