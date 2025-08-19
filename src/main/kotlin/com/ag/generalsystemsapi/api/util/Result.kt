package com.ag.generalsystemsapi.api.util

data class Result<T>(
    val success: Boolean,
    val msg: String? = null,
    val data: T? = null
)
