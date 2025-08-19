package com.ag.generalsystemsapi.api.model.view

import com.ag.generalsystemsapi.api.model.SystemRolesModel

class UserRolesView (
    var userRoleId: Long? = null,
    var userId: Long? = null,
    var userRolesRlId: SystemRolesModel?,
    var userRoleActive: Boolean?,
)