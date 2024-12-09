package com.composetest.core.domain.errors

sealed class ApiError : Throwable() {

    data class Request(override val message: String) : ApiError()

    data class Unknown(override val message: String?) : ApiError()

    class Unauthorized : ApiError() {
        override val message = "Client not authorized"
    }

    class Network : ApiError() {
        override val message = "No internet connection"
    }
}