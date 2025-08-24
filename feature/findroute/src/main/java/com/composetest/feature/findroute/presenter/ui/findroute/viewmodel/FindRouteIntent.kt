package com.composetest.feature.findroute.presenter.ui.findroute.viewmodel

import com.composetest.core.ui.interfaces.Intent

internal sealed interface FindRouteIntent : Intent<FindRouteIntentReceiver> {
    data object OnResume : FindRouteIntent {
        override fun execute(intentReceiver: FindRouteIntentReceiver) {
            intentReceiver.onResume()
        }
    }
}