package com.ag.generalsystemsapi.api.exception

import java.util.*

class ErrorDetails(timestamp: Date, message: String, details: String, stackTrace: String) {
    val timestamp: Date
    val message: String
    val details: String
    val stackTrace: String//Array<StackTraceElement>

    init {
        this.timestamp = timestamp
        this.message = message
        this.details = details
        this.stackTrace = stackTrace
    }
}