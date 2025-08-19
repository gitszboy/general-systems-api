package com.ag.generalsystemsapi.api.util

import java.time.format.DateTimeFormatter

object DateFormatter {

    fun defaultDateTimeFormatter(): DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyyMMddHHmmss")

    fun customDateTimeFormatter(): DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy")
}