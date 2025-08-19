package com.ag.generalsystemsapi.api.model.payload

class RegisterUserRequest (
    var userFullName: String,
    var email: String,
    var userName: String,
    var password: String,
    var agentCode: Long,
)