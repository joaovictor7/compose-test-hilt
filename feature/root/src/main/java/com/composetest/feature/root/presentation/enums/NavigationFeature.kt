package com.composetest.feature.root.presentation.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.router.destination.configuration.ConfigurationDestination
import com.composetest.core.router.destination.exchange.ExchangeListDestination
import com.composetest.core.router.destination.home.HomeDestination
import com.composetest.core.router.destination.news.NewsListDestination
import com.composetest.core.router.destination.profile.ProfileDestination
import com.composetest.core.router.destination.weatherforecast.WeatherForecastDestination
import com.composetest.core.router.interfaces.Destination
import com.composetest.feature.root.domain.enums.Feature
import com.composetest.core.designsystem.R as DesignSystemRes
import com.composetest.feature.configuration.R as ConfigurationRes
import com.composetest.feature.exchange.R as ExchangeRes
import com.composetest.feature.home.R as HomeRes
import com.composetest.feature.news.R as NewsRes
import com.composetest.feature.profile.R as AccountRes
import com.composetest.feature.weatherforecast.R as WeatherForecastRes

internal enum class NavigationFeature(
    val feature: Feature,
    val destination: Destination,
    val navigationLocal: NavigationLocal,
    @param:DrawableRes val iconId: Int,
    @param:StringRes val textId: Int? = null,
) {
    HOME(
        Feature.HOME,
        HomeDestination,
        NavigationLocal.BOTTOM,
        DesignSystemRes.drawable.ic_house_filled,
        HomeRes.string.home_title
    ),
    CONFIGURATION(
        Feature.CONFIGURATION,
        ConfigurationDestination,
        NavigationLocal.BOTTOM,
        DesignSystemRes.drawable.ic_config_filled,
        ConfigurationRes.string.configuration_title
    ),
    WEATHER_FORECAST(
        Feature.WEATHER_FORECAST,
        WeatherForecastDestination,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_partly_cloudy_medium,
        WeatherForecastRes.string.weather_forecast_title
    ),
    NEWS(
        Feature.NEWS,
        NewsListDestination,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_news_medium,
        NewsRes.string.news_title
    ),
    PROFILE(
        Feature.PROFILE,
        ProfileDestination,
        NavigationLocal.MODAL_DRAWER,
        AccountRes.string.account_title
    ),
    EXCHANGE(
        Feature.EXCHANGE,
        ExchangeListDestination,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_exchange_medium,
        ExchangeRes.string.exchange_title
    );

    val noText get() = textId == null

    companion object {
        val bottomNavigationFeatures get() = entries.filter { it.navigationLocal == NavigationLocal.BOTTOM }
        val modalDrawerFeatures get() = entries.filter { it.navigationLocal == NavigationLocal.MODAL_DRAWER }
    }
}