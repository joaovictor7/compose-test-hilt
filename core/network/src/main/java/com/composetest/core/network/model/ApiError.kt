package com.composetest.core.network.model

sealed class ApiError : Throwable() {

    data class Request(override val message: String) : ApiError()

    data class Unknown(
        private val error: Throwable
    ) : ApiError() {
        override val cause = error
        override val message = error.message
    }

    class Unauthorized : ApiError() {
        override val message = "Client not authorized"
    }

    class Network : ApiError() {
        override val message = "No internet connection"
    }
}