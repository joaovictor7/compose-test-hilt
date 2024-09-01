package com.composetest.core.designsystem.extensions

import android.app.Activity
import android.content.Context

val Context.asActivity get() = this as? Activity