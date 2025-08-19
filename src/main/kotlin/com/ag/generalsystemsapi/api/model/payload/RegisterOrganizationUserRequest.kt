package com.ag.generalsystemsapi.api.model.payload

class RegisterOrganizationUserRequest (
    var userFullName: String,
    var email: String,
    var orgCode: Long,
)