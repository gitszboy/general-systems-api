package com.ag.generalsystemsapi.api.model.view

import java.util.ArrayList
import java.util.Date

class UsersView (
    var userId: Long? = null,
    var userFullName: String? = null,
    var username: String? = null,
    var userEmail: String? = null,
    var agentCode: Long? = null,
    var agentId: String? = null,
    var agentName: String? = null,
    //var roles: List<String>? = null,
    var clientCode: Long? = null,
    var tpClientCode: Long? = null,
    var clientName: String? = null,
    var clientDob: Date? = null,
    var orgCode: Long? = null,
    var orgName: String? = null,
    var roles: ArrayList<UserRoleModulesView>? = null
)