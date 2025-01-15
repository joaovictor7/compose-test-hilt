package com.composetest.feature.home.ui.home

import com.composetest.core.ui.interfaces.Command

internal sealed interface HomeCommand : Command<HomeCommandReceiver> {
}