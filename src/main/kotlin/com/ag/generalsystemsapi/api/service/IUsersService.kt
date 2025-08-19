package com.ag.generalsystemsapi.api.service

import com.ag.generalsystemsapi.api.model.SystemAreasModel
import com.ag.generalsystemsapi.api.model.SystemRoleAreasModel
import com.ag.generalsystemsapi.api.model.SystemRolesModel
import com.ag.generalsystemsapi.api.model.payload.*
import com.ag.generalsystemsapi.api.model.view.*
import com.ag.generalsystemsapi.api.util.Result

interface IUsersService {

    fun RegisterSystemUser(userRequest: RegisterUserRequest)

    fun AuthenticateSystemUser(userRequest: AuthenticateUserRequest)

    fun findUserDetails (username: String?) : UsersView
    fun RegisterAgentUser(userRequest: RegisterAgentUserRequest)
    fun AuthenticateAgentUser(userRequest: AuthenticateUserRequest)
    fun InitiateResetAgentPassword(agentCode: String) : Result<String>
    fun resetAgentPassword(agentCode: String,
                           resetCode: Long,
                           password: String) : Result<String>
    fun RegisterClientUser(userRequest: RegisterClientUserRequest)
    fun RegisterTpClientUser(idNumber: String)
    fun InitiateResetClientPassword(clientIDNumber: String) : Result<String>
    fun resetClientPassword(clientIDNumber: String,
                            resetCode: Long,
                            password: String) : Result<String>
    fun emailSendService(receipent: String): Result<String>
    fun findSystemModules() : Result<Iterable<SystemAreasModel>>
    fun findSystemRoles() : Result<Iterable<SystemRolesModel>>
    fun findSystemRoleAreas(systemRoleId: Long) : Result<Iterable<SystemRoleAreasModel>>
    fun saveSystemRole(systemRole: SystemRolesModel)
    fun assignUserRoles(userRoles: Iterable<UserRolesRequest>)
    fun assignRolesAreas(roleAreas: Iterable<SystemRoleAreasRequest>)
    fun findUserRoles(userId: Long) : Result<Iterable<UserRolesView>>
    fun findSystemUsers(userType: String) : Result<Iterable<BasicUsersView>>
    fun RegisterProviderUser(userRequest: RegisterOrganizationUserRequest) : Result<String>
    fun RegisterStaffUser(userRequest: RegisterStaffUserRequest) : Result<String>
    fun InitiateResetUserPassword(email: String) : Result<String>
    fun resetUserPassword(email: String,
                          resetCode: Long,
                          password: String) : Result<String>
}