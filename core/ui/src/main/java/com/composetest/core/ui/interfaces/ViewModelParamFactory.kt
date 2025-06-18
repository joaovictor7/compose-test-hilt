package com.composetest.core.ui.interfaces

import androidx.lifecycle.ViewModel

interface ViewModelParamFactory<P, VM : ViewModel> {
    fun create(param: P?): VM
}