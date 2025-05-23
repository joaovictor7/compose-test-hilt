package com.composetest.ui.main

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface MainCommandReceiver : CommandReceiver<MainCommandReceiver> {
    fun verifySession(currentRoute: String?)
    fun fetchRemoteConfig()
    fun dismissAlertDialog()
}