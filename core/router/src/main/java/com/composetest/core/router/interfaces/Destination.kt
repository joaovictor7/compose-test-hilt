package com.composetest.core.router.interfaces

internal interface Destination {
    val asRoute get() = this::class.qualifiedName
}