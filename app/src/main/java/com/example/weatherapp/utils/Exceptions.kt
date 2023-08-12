package com.example.weatherapp.utils

open class AppException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}

class ConnectionException(cause: Throwable) : AppException(cause = cause)

class BackendException(cause: Throwable) : AppException(cause = cause)
