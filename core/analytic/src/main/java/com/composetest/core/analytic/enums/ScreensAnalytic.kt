package com.composetest.core.analytic.enums

import com.composetest.core.analytic.AnalyticScreen

enum class ScreensAnalytic(override val screen: String) : AnalyticScreen {
    MAIN("main"),
    CONFIGURATION("configuration"),
    SECURITY_CONFIGURATION("security_configuration"),
    THEME_CONFIGURATION("theme_configuration"),
    EXCHANGE_DETAIL("exchange_detail"),
    EXCHANGE_LIST("exchange_list"),
    HOME("home"),
    LOGIN("login"),
    FULL_NEWS("full_news"),
    NEWS_LIST("news_list"),
    ACCOUNT("account"),
    ROOT("root"),
    WEATHER_FORECAST("weather_forecast")
}