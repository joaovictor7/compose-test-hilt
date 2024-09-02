package com.composetest.feature.home.ui.home2

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface Home2CommandReceiver : CommandReceiver<Home2CommandReceiver> {
    fun returnHome()
}