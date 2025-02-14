package com.composetest.ui

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface MainCommandReceiver : CommandReceiver<MainCommandReceiver> {
    fun verifySession()
    fun fetchRemoteConfig()
    fun dismissAlertDialog()
}