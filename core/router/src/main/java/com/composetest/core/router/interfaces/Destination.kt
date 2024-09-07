package com.composetest.core.router.interfaces

interface Destination {
    val asRoute get() = this::class.qualifiedName
}