package com.ag.generalsystemsapi.api.model.responses

import com.ag.generalsystemsapi.api.model.view.UsersView
import java.io.Serializable


class AuthenticationResponse(
    val token: String?,
    val user: UsersView?,
) : Serializable
