package com.ag.generalsystemsapi.api.model.payload

class AuthenticateUserRequest (
    var userName: String,
    var password: String,
    var system: String? = null
)