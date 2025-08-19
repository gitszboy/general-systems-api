package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.AgentsModel
import com.ag.generalsystemsapi.api.model.SystemAreasModel
import com.ag.generalsystemsapi.api.model.SystemRoleAreasModel
import com.ag.generalsystemsapi.api.model.SystemRolesModel
import com.ag.generalsystemsapi.api.model.view.*
import com.ag.generalsystemsapi.api.service.ILoginService
import com.ag.generalsystemsapi.api.service.IUsersService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.ag.generalsystemsapi.api.util.Result

@RestController
@RequestMapping("/login")
@Tag(name = "Login Controller", description = "Endpoint - This service manages calls relating to authentication of Agents")
@CrossOrigin(origins = ["*"])
class LoginController {

    @Autowired
    lateinit var loginService : ILoginService

    @Autowired
    lateinit var iUsersService: IUsersService

    /*@Operation(summary = "Login Agent", description = "Authenticates an Agent")
    @RequestMapping(value = ["/agentAuth"], method = [RequestMethod.POST])
    fun authAgent(
        @RequestBody loginRequest: LoginRequest
    ) : ResponseEntity<AgentResponse> {
        val agent = loginService.agentAuthentication(loginRequest)

        return ResponseEntity<AgentResponse>(agent, HttpStatus.OK)
    }

    @Operation(summary = "Agent Password Reset", description = "Agent Password Reset")
    @RequestMapping(value = ["/resetAgentPassword"], method = [RequestMethod.POST])
    fun resetAgentPassword(
        @RequestParam(name= "agentCode", required = false) agentCode: String,
        @RequestParam(name= "resetCode", required = false) resetCode: Long,
        @RequestParam(name= "password", required = false) password: String
    ) : Result<String> {
        return loginService.resetAgentPassword(agentCode, resetCode, password)
    }

    @Operation(summary = "Initiate Password Reset", description = "Initiate Password Reset")
    @RequestMapping(value = ["/InitiateResetAgentPassword"], method = [RequestMethod.POST])
    fun InitiateResetAgentPassword(
        @RequestParam(name= "agentCode", required = false) agentCode: String
    ) : Result<String> {
        return loginService.InitiateResetAgentPassword(agentCode)
    }

    @Operation(summary = "Find All Agents", description = "Fetches All Agents")
    @GetMapping("/findAllAgents", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllAgents(): ResponseEntity<Iterable<AgentsModel>> = ResponseEntity<Iterable<AgentsModel>>(loginService.findAllAgents(), HttpStatus.OK)

    @Operation(summary = "Populate Quote Agents", description = "Populate Agents")
    @GetMapping("/populateAgents", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun populateAgents(): ResponseEntity<Void> {
        loginService.populateAgents()
        return ResponseEntity<Void>(HttpStatus.OK)
    }*/

    @Operation(summary = "Save System Role", description = "Save System Role")
    @PostMapping("/saveSystemRole")
    fun saveSystemRole(@RequestBody systemRole: SystemRolesModel) : ResponseEntity<*>{
        iUsersService.saveSystemRole(systemRole)
        return ResponseEntity("Role Created successfully!.", HttpStatus.OK)
    }

    @Operation(summary = "Assign User Roles", description = "Assign User Roles")
    @PostMapping("/assignUserRoles")
    fun assignUserRoles(@RequestBody userRoles: ArrayList<UserRolesRequest>) : ResponseEntity<*>{
        iUsersService.assignUserRoles(userRoles)
        return ResponseEntity("User Roles Assigned successfully!.", HttpStatus.OK)
    }

    @Operation(summary = "Assign Role Modules", description = "Assign Role Modules")
    @PostMapping("/assignRoleModules")
    fun assignRolesModules(@RequestBody roleAreas: ArrayList<SystemRoleAreasRequest>) : ResponseEntity<*>{
        iUsersService.assignRolesAreas(roleAreas)
        return ResponseEntity("Modules Assigned to Role successfully!.", HttpStatus.OK)
    }

    @Operation(summary = "Find System Modules", description = "Fetches System Modules")
    @GetMapping("/findSystemModules", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findSystemModules(): Result<Iterable<SystemAreasModel>> = iUsersService.findSystemModules()

    @Operation(summary = "Find System Roles", description = "Fetches System Roles")
    @GetMapping("/findSystemRoles", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findSystemRoles(): Result<Iterable<SystemRolesModel>> = iUsersService.findSystemRoles()

    @Operation(summary = "Find System Role Modules", description = "Fetches System Role Modules")
    @GetMapping("/findSystemRoleModules", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findSystemRoleModules(@RequestParam(name= "systemRoleId", required = true) systemRoleId: Long): Result<Iterable<SystemRoleAreasModel>> = iUsersService.findSystemRoleAreas(systemRoleId)

    @Operation(summary = "Find User Roles", description = "Fetches User Roles")
    @GetMapping("/findUserRoles", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findUserRoles(@RequestParam(name= "userId", required = true) userId: Long): Result<Iterable<UserRolesView>> = iUsersService.findUserRoles(userId)

    @Operation(summary = "Find System Users", description = "Fetches System Users")
    @GetMapping("/findSystemUsers", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findSystemUsers(@RequestParam(name= "systemType", required = true) systemType: String): Result<Iterable<BasicUsersView>> = iUsersService.findSystemUsers(systemType)


}