package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.payload.*
import com.ag.generalsystemsapi.api.model.responses.AuthenticationResponse
import com.ag.generalsystemsapi.api.service.*
import com.ag.generalsystemsapi.api.service.impl.JwtUtilImpl
import com.ag.generalsystemsapi.api.service.impl.UserDetailsAuthServiceImpl
import com.ag.generalsystemsapi.api.util.Result
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller", description = "Endpoint - This service manages calls relating to Authentication of Users")
@CrossOrigin(origins = ["*"])
class AuthenticationController {

    @Autowired
    lateinit var iUsersService: IUsersService

    @Autowired
    lateinit var jwtUtilImpl: JwtUtilImpl

    @Autowired
    lateinit var userDetailsAuthServiceImpl: UserDetailsAuthServiceImpl

    @Autowired
    lateinit var loginService: ILoginService

    @Operation(summary = "Authenticate System Users", description = "Authenticate System Users")
    @PostMapping("/AuthenticateSystemUser")
    fun AuthenticateSystemUser(@RequestBody userRequest: AuthenticateUserRequest) : ResponseEntity<*>{
        val token: String?
        iUsersService.AuthenticateSystemUser(userRequest)
        val userDetails = userDetailsAuthServiceImpl.loadUserByUsername(userRequest.userName)
        val user = iUsersService.findUserDetails(userRequest.userName)
        token = jwtUtilImpl.generateToken(userDetails)
        val response = AuthenticationResponse(
            token = token,
            user = user,
        )
        return ResponseEntity.ok(response)
    }

    /*@Operation(summary = "Register System Users", description = "Register System Users")
    @PostMapping("/RegisterSystemUser")
    fun RegisterSystemUser(@RequestBody userRequest: RegisterUserRequest) : ResponseEntity<*>{
        iUsersService.RegisterSystemUser(userRequest)
        return ResponseEntity("User registered successfully!.", HttpStatus.OK)
    }

    @Operation(summary = "Register TP System Users", description = "Register TP System Users")
    @PostMapping("/RegisterTpClientUser")
    fun RegisterTpClientUser(@RequestParam(name= "idNumber", required = false) idNumber: String) : ResponseEntity<*>{
        iUsersService.RegisterTpClientUser(idNumber)
        return ResponseEntity("User registered successfully!.", HttpStatus.OK)
    }


    @Operation(summary = "Agent Password Reset", description = "Agent Password Reset")
    @RequestMapping(value = ["/resetAgentPassword"], method = [RequestMethod.POST])
    fun resetAgentPassword(
        @RequestBody userRequest: ResetRequest
    ) : ResponseEntity<*> {
        val result = iUsersService.resetAgentPassword(userRequest.code, userRequest.resetCode, userRequest.password)
        return if(result.success){
            ResponseEntity("Password reset successfully.", HttpStatus.OK)
        }else{
            ResponseEntity(result.msg, HttpStatus.BAD_REQUEST)
        }
    }

    @Operation(summary = "Initiate Password Reset", description = "Initiate Password Reset")
    @RequestMapping(value = ["/InitiateResetAgentPassword"], method = [RequestMethod.POST])
    fun InitiateResetAgentPassword(
        @RequestParam(name= "agentCode", required = false) agentCode: String
    ) : Result<String> {
        return iUsersService.InitiateResetAgentPassword(agentCode)
    }

    @Operation(summary = "Client Password Reset", description = "Client Password Reset")
    @RequestMapping(value = ["/resetClientPassword"], method = [RequestMethod.POST])
    fun resetClientPassword(
        @RequestBody userRequest: ResetRequest
    ) : ResponseEntity<*> {
        val result =  iUsersService.resetClientPassword(userRequest.code, userRequest.resetCode, userRequest.password)
        return if(result.success){
            ResponseEntity("Password reset successfully.", HttpStatus.OK)
        }else{
            ResponseEntity(result.msg, HttpStatus.BAD_REQUEST)
        }
    }

    @Operation(summary = "Initiate Password Reset", description = "Initiate Password Reset")
    @RequestMapping(value = ["/InitiateResetClientPassword"], method = [RequestMethod.POST])
    fun InitiateResetClientPassword(
        @RequestParam(name= "clientIDNumber", required = false) clientIDNumber: String
    ) : Result<String> {
        return iUsersService.InitiateResetClientPassword(clientIDNumber)
    }

    @Operation(summary = "Email Service", description = "Email Service")
    @RequestMapping(value = ["/emailSendService"], method = [RequestMethod.POST])
    fun emailSendService(
        @RequestParam(name= "receipient", required = false) receipient: String
    ) : Result<String> {
        return iUsersService.emailSendService(receipient)
    }*/

    @Operation(summary = "Register Provider User", description = "Register Provider User")
    @RequestMapping(value = ["/RegisterProviderUser"], method = [RequestMethod.POST])
    fun RegisterProviderUser(
        @RequestBody userRequest: RegisterOrganizationUserRequest
    ) : ResponseEntity<*> {
        val result = iUsersService.RegisterProviderUser(userRequest)
        return if(result.success){
            ResponseEntity(result.msg, HttpStatus.OK)
        }else{
            ResponseEntity(result.msg, HttpStatus.BAD_REQUEST)
        }
    }

    @Operation(summary = "Register Staff User", description = "Register Staff User")
    @RequestMapping(value = ["/RegisterStaffUser"], method = [RequestMethod.POST])
    fun RegisterStaffUser(
        @RequestBody userRequest: RegisterStaffUserRequest
    ) : ResponseEntity<*> {
        val result = iUsersService.RegisterStaffUser(userRequest)
        return if(result.success){
            ResponseEntity(result.msg, HttpStatus.OK)
        }else{
            ResponseEntity(result.msg, HttpStatus.BAD_REQUEST)
        }
    }

    @Operation(summary = "Initiate User Password Reset", description = "Initiate User Password Reset")
    @RequestMapping(value = ["/InitiateResetUserPassword"], method = [RequestMethod.POST])
    fun InitiateResetUserPassword(
        @RequestParam(name= "emailAddress", required = false) emailAddress: String
    ) : Result<String> {
        return iUsersService.InitiateResetUserPassword(emailAddress)
    }

    @Operation(summary = "User Password Reset", description = "User Password Reset")
    @RequestMapping(value = ["/resetUserPassword"], method = [RequestMethod.POST])
    fun resetUserPassword(
        @RequestBody userRequest: ResetRequest
    ) : ResponseEntity<*> {
        val result =  iUsersService.resetUserPassword(userRequest.code, userRequest.resetCode, userRequest.password)
        return if(result.success){
            ResponseEntity("Password reset successfully.", HttpStatus.OK)
        }else{
            ResponseEntity(result.msg, HttpStatus.BAD_REQUEST)
        }
    }
}