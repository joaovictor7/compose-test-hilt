package com.composetest.feature.root.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.domain.enums.Feature
import com.composetest.core.router.destinations.configuration.ConfigurationDestination
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.destinations.news.NewsListDestination
import com.composetest.core.router.destinations.profile.ProfileDestination
import com.composetest.core.router.destinations.weatherforecast.WeatherForecastDestination
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.designsystem.R as DesignSystemResources
import com.composetest.feature.configuration.R as ConfigurationResources
import com.composetest.feature.home.R as HomeResources
import com.composetest.feature.news.R as NewsResources
import com.composetest.feature.profile.R as ProfileResources
import com.composetest.feature.weatherforecast.R as WeatherForecastResources

internal enum class NavigationFeature(
    val feature: Feature,
    val destination: Destination,
    val navigationLocal: NavigationLocal,
    @DrawableRes val iconId: Int,
    @StringRes val textId: Int? = null,
) {
    HOME(
        Feature.HOME,
        HomeDestination,
        NavigationLocal.BOTTOM,
        DesignSystemResources.drawable.ic_house_filled,
        HomeResources.string.home_title
    ),
    CONFIGURATION(
        Feature.CONFIGURATION,
        ConfigurationDestination,
        NavigationLocal.BOTTOM,
        DesignSystemResources.drawable.ic_config_filled,
        ConfigurationResources.string.configuration_title
    ),
    WEATHER_FORECAST(
        Feature.WEATHER_FORECAST,
        WeatherForecastDestination,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemResources.drawable.ic_partly_cloudy_big,
        WeatherForecastResources.string.weather_forecast_title
    ),
    NEWS(
        Feature.NEWS,
        NewsListDestination,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemResources.drawable.ic_news_big,
        NewsResources.string.news_title
    ),
    PROFILE(
        Feature.PROFILE,
        ProfileDestination,
        NavigationLocal.MODAL_DRAWER,
        ProfileResources.string.profile_title
    );

    val noText get() = textId == null

    companion object {
        val bottomNavigationFeatures get() = entries.filter { it.navigationLocal == NavigationLocal.BOTTOM }
        val modalDrawerFeatures get() = entries.filter { it.navigationLocal == NavigationLocal.MODAL_DRAWER }
    }
}