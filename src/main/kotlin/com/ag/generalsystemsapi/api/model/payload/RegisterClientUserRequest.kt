package com.ag.generalsystemsapi.api.model.payload

class RegisterClientUserRequest(
    var userFullName: String,
    var idNumber: String? = null,
    var userName: String,
    var password: String
)