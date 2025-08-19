package com.ag.generalsystemsapi.api.model.responses

data class MpesaErrorResponse(
    val requestId: String?,
    val errorCode: String?,
    val errorMessage: String?
)