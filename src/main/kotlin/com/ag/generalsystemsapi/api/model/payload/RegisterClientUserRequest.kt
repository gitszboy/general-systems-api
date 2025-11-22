package com.ag.generalsystemsapi.api.model.payload

class RegisterClientUserRequest(
    var userFullName: String,
    var idNumber: String,
    var telephone: String,
    var emailAddress: String,
    var password: String
)