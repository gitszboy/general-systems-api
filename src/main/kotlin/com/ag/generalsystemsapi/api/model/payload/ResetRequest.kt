package com.ag.generalsystemsapi.api.model.payload

class ResetRequest (
    var code: String,
    var resetCode: Long,
    var password: String,
)