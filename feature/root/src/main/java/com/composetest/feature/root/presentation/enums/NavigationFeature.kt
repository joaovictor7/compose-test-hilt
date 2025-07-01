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
import com.composetest.feature.root.R
import com.composetest.feature.root.domain.enums.Feature
import com.composetest.core.designsystem.R as DesignSystemRes

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
        R.string.feature_home_title,
    ),
    CONFIGURATION(
        Feature.CONFIGURATION,
        ConfigurationDestination,
        NavigationLocal.BOTTOM,
        DesignSystemRes.drawable.ic_config_filled,
        R.string.feature_configuration_title,
    ),
    WEATHER_FORECAST(
        Feature.WEATHER_FORECAST,
        WeatherForecastDestination,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_partly_cloudy_medium,
        R.string.feature_weather_forecast_title,
    ),
    NEWS(
        Feature.NEWS,
        NewsListDestination,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_news_medium,
        R.string.feature_news_title,
    ),
    PROFILE(
        Feature.PROFILE,
        ProfileDestination,
        NavigationLocal.MODAL_DRAWER,
        R.string.username_unregistered,
    ),
    EXCHANGE(
        Feature.EXCHANGE,
        ExchangeListDestination,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_exchange_medium,
        R.string.feature_exchange_title,
    );

    val noText get() = textId == null

    companion object {
        val bottomNavigationFeatures get() = entries.filter { it.navigationLocal == NavigationLocal.BOTTOM }
        val modalDrawerFeatures get() = entries.filter { it.navigationLocal == NavigationLocal.MODAL_DRAWER }
    }
}