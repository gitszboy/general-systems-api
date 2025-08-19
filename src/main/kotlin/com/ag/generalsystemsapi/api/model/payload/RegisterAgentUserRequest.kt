package com.ag.generalsystemsapi.api.model.payload

class RegisterAgentUserRequest (
    var userFullName: String,
    var email: String? = null,
    var userName: String,
    var password: String,
    var orgCode: Long?,
)