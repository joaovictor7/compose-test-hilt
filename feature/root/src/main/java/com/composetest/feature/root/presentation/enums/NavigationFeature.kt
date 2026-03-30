package com.composetest.feature.root.presentation.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.navkey.configuration.ConfigurationNavKey
import com.composetest.core.router.navkey.exchange.ExchangeListNavKey
import com.composetest.core.router.navkey.form.FormNavKey
import com.composetest.core.router.navkey.home.HomeNavKey
import com.composetest.core.router.navkey.news.NewsListNavKey
import com.composetest.core.router.navkey.product.ProductListNavKey
import com.composetest.core.router.navkey.profile.ProfileNavKey
import com.composetest.core.router.navkey.weatherforecast.WeatherForecastNavKey
import com.composetest.feature.root.R
import com.composetest.feature.root.domain.enums.Feature
import com.composetest.core.designsystem.R as DesignSystemRes

internal enum class NavigationFeature(
    val feature: Feature,
    val navKey: NavKey,
    val navigationLocal: NavigationLocal,
    @param:DrawableRes val iconId: Int,
    @param:StringRes val textId: Int? = null,
) {
    HOME(
        Feature.HOME,
        HomeNavKey,
        NavigationLocal.BOTTOM,
        DesignSystemRes.drawable.ic_house_filled,
        R.string.feature_home_title,
    ),
    CONFIGURATION(
        Feature.CONFIGURATION,
        ConfigurationNavKey,
        NavigationLocal.BOTTOM,
        DesignSystemRes.drawable.ic_config_filled,
        R.string.feature_configuration_title,
    ),
    WEATHER_FORECAST(
        Feature.WEATHER_FORECAST,
        WeatherForecastNavKey,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_partly_cloudy_medium,
        R.string.feature_weather_forecast_title,
    ),
    NEWS(
        Feature.NEWS,
        NewsListNavKey,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_news_medium,
        R.string.feature_news_title,
    ),
    PROFILE(
        Feature.PROFILE,
        ProfileNavKey,
        NavigationLocal.MODAL_DRAWER,
        R.string.username_unregistered,
    ),
    EXCHANGE(
        Feature.EXCHANGE,
        ExchangeListNavKey,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_exchange_medium,
        R.string.feature_exchange_title,
    ),
    PRODUCT(
        Feature.PRODUCT,
        ProductListNavKey,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_grocery_store,
        R.string.feature_product_title,
    ),
    FORM(
        Feature.FORM,
        FormNavKey,
        NavigationLocal.MODAL_DRAWER,
        DesignSystemRes.drawable.ic_doc,
        R.string.feature_form_title,
    );

    val noText get() = textId == null

    companion object {
        val bottomNavigationFeatures get() = entries.filter { it.navigationLocal == NavigationLocal.BOTTOM }
        val modalDrawerFeatures get() = entries.filter { it.navigationLocal == NavigationLocal.MODAL_DRAWER }
    }
}