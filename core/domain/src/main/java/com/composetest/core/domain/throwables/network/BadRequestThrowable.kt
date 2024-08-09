package com.composetest.core.domain.throwables.network

class BadRequestThrowable(
    override val message: String
) : Throwable()