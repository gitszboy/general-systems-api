package com.ag.generalsystemsapi.api.model.payload

class MpesaRequest (
    var code: Long,
    var telephone: String,
    var amount: Double? = null,
)