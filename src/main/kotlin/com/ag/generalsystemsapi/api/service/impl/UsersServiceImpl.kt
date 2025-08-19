package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.exception.ResourceNotFoundException
import com.ag.generalsystemsapi.api.helpers.NotificationResourceHelper
import com.ag.generalsystemsapi.api.helpers.PasswordValidationResult
import com.ag.generalsystemsapi.api.model.*
import com.ag.generalsystemsapi.api.model.payload.*
import com.ag.generalsystemsapi.api.model.view.*
import com.ag.generalsystemsapi.api.repository.*
import com.ag.generalsystemsapi.api.service.IUsersService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import com.ag.generalsystemsapi.thirdparty.repository.TpClientsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


@Service
class UsersServiceImpl: IUsersService {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var systemRolesRepository: SystemRolesRepository

    @Autowired
    lateinit var userRolesRepository: UserRolesRepository

    @Autowired
    lateinit var agentAccountsRepository: AgentAccountsRepository

    @Autowired
    lateinit var agentsRepository: AgentsRepository

    @Autowired
    lateinit var notificationResourceHelper: NotificationResourceHelper

    @Autowired
    lateinit var clientAccountsRepo: ClientAccountsRepository

    @Autowired
    lateinit var clientsRepo: ClientsRepository

    @Autowired
    lateinit var tpClientsRepo: TpClientsRepository

    @Autowired
    lateinit var systemRoleAreasRepo: SystemRoleAreasRepository

    @Autowired
    lateinit var systemAreasRepo: SystemAreasRepository

    @Autowired
    lateinit var organizationRepo: OrganizationRepository

    @Autowired
    lateinit var organizationAccountsRepo: OrganizationAccountsRepository

    override fun RegisterSystemUser(userRequest: RegisterUserRequest) {

        // add check for username exists in a DB
        if (usersRepository.existsByUsername(userRequest.userName)) {
            //return ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST)
            throw Exception("Username is already taken!")
        }

        // add check for email exists in DB
        if (usersRepository.existsByUserEmail(userRequest.email)) {
            //return ResponseEntity("Email is already taken!", HttpStatus.BAD_REQUEST)
            throw Exception("Email is already taken!")
        }

        //Check password conformance.
        val validationResult = passwordValidator(userRequest.password)
        if (!validationResult.isValid) {
            val message = validationResult.errors.joinToString(" ")
            throw IllegalArgumentException("Invalid password: $message")
        }

        // add check for email exists in DB
        /*if (!agentsRepository.existsByAgentCode(userRequest.agentCode)) {
            //return ResponseEntity("Email is already taken!", HttpStatus.BAD_REQUEST)
            throw ResourceNotFoundException("Agent does not exist.", userRequest.userName, userRequest.agentCode)
        }*/

        //val agent = agentsRepository.findByAgentCode(userRequest.agentCode)

        // create user object
        var user = UsersModel(
            username = userRequest.userName,
            userFullName = userRequest.userFullName,
            userEmail = userRequest.email,
            userActive = true,
            password = passwordEncoder.encode(userRequest.password)
        )

        user = usersRepository.save(user)

        //Fetch and attach Default Roles
        for (r in systemRolesRepository.findByRoleDefault(true)){
            val userRole = UserRolesModel(
                userRolesRlId = r,
                userRoleActive = true,
                userRolesUserId = user
            )
            userRolesRepository.save(userRole)
        }

        //Save Agent Account
        /*val agentAcc = AgentAccountsModel(
            agnAccAgentId = agent,
            agnAccUserId = user,
            agnAccAgentActive = "Y",
            agnAccAgentActivationDate = Calendar.getInstance().time
        )
        agentAccountsRepository.save(agentAcc)*/

    }

    override fun AuthenticateSystemUser(userRequest: AuthenticateUserRequest) {
        val auth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userRequest.userName,
            userRequest.password))
        SecurityContextHolder.getContext().authentication = auth;
    }

    override fun RegisterAgentUser(userRequest: RegisterAgentUserRequest) {

        // add check for username exists in a DB
        if (usersRepository.existsByUsername(userRequest.userName)) {
            //return ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST)
            throw Exception("Agent Code is already assigned to another user!")
        }

        // add check for email exists in DB
        if (!agentsRepository.existsByAgentShtDesc(userRequest.userName)) {
            //return ResponseEntity("Email is already taken!", HttpStatus.BAD_REQUEST)
            throw ResourceNotFoundException("Agent does not exist.", userRequest.userName, 0)
        }

        val agent = agentsRepository.findByAgentShtDesc(userRequest.userName)
        val agnActive = agent?.agentStatus.equals("ACTIVE")

        //Check password conformance.
        val validationResult = passwordValidator(userRequest.password)
        if (!validationResult.isValid) {
            val message = validationResult.errors.joinToString(" ")
            throw IllegalArgumentException("Invalid password: $message")
        }


        // create user object
        var user = UsersModel(
            username = userRequest.userName,
            userFullName = userRequest.userFullName,
            userEmail = agent?.agentEmail,
            userActive = agnActive,
            password = passwordEncoder.encode(userRequest.password),
            userType = "A"
        )

        user = usersRepository.save(user)

        //Fetch and attach Default Roles
        /*for (r in systemRolesRepository.findByRoleDefault(true)){
            val userRole = UserRolesModel(
                userRolesRlId = r,
                userRoleActive = true,
                userRolesUserId = user
            )
            userRolesRepository.save(userRole)
        }*/

        val role = systemRolesRepository.findByRoleName("AGENT")
            .orElseThrow { Exception("role not found") }
        val userRole = UserRolesModel(
            userRolesRlId = role,
            userRoleActive = true,
            userRolesUserId = user
        )
        userRolesRepository.save(userRole)


        //Save Agent Account
        val agentAcc = AgentAccountsModel(
            agnAccAgentId = agent,
            agnAccUserId = user,
            agnAccAgentActive = agnActive.toString(),
            agnAccAgentActivationDate = Calendar.getInstance().time
        )
        agentAccountsRepository.save(agentAcc)

    }

    fun generateRandomAlphanumeric(length: Int): String {
        val ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { Random.nextInt(0, ALPHANUMERIC_CHARS.length) }
            .map(ALPHANUMERIC_CHARS::get)
            .joinToString("")
    }

    override fun RegisterProviderUser(userRequest: RegisterOrganizationUserRequest) : Result<String> {

        // add check for organization exists in a DB
        if (!organizationRepo.existsByOrgCode(userRequest.orgCode)) {
            //return ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST)
            return ResultFactory.getFailResult("Organization does not exist.")
        }

        // add check for username exists in a DB
        if (usersRepository.existsByUsername(userRequest.email)) {
            //return ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST)
            return ResultFactory.getFailResult("Username is already exists.")
        }

        val organization = organizationRepo.findByOrgCode(userRequest.orgCode)
        val agnActive = organization.orgStatus.equals("ACTIVE")

        val password = generateRandomAlphanumeric(6)
        //Check password conformance.
        /*
        val validationResult = passwordValidator(userRequest.password)
        if (!validationResult.isValid) {
            val message = validationResult.errors.joinToString(" ")
            return ResultFactory.getFailResult("Invalid password: $message")
        }*/


        try {
            // create user object
            var user = UsersModel(
                username = userRequest.email,
                userFullName = userRequest.userFullName,
                userEmail = userRequest.email,
                userActive = agnActive,
                password = passwordEncoder.encode(password),
                userType = "P"
            )

            user = usersRepository.save(user)

            //Fetch and attach Default Roles
            /*for (r in systemRolesRepository.findByRoleDefault(true)){
            val userRole = UserRolesModel(
                userRolesRlId = r,
                userRoleActive = true,
                userRolesUserId = user
            )
            userRolesRepository.save(userRole)
        }*/

            val role = systemRolesRepository.findByRoleName("PROVIDER")
                .orElseThrow { Exception("role not found") }
            val userRole = UserRolesModel(
                userRolesRlId = role,
                userRoleActive = true,
                userRolesUserId = user
            )
            userRolesRepository.save(userRole)


            //Save Provider Account
            val orgAcc = OrganizationAccountsModel(
                orgAccOrganizationId = organization,
                orgAccUserId = user,
                orgAccActive = agnActive.toString(),
                agnAccActivationDate = Calendar.getInstance().time
            )
            organizationAccountsRepo.save(orgAcc)

            //Send Email Notification on Password to User.
            val receipent = userRequest.email
            val emailSubject = "Cannon Insurance Provider Portal Password Reset"
            val emailBody: String =
                "<p>Dear ${userRequest.userFullName}.</p>" +
                        "<p>Kindly utilize the Password below to access the Cannon Insurance Provider Portal.</p>" +
                        "<p><b>Password: ${password}</b></p>" +
                        "<p>Cannon Insurance Limited.</p>"
            notificationResourceHelper.sendSimpleEmail(receipent,emailSubject, emailBody, true)
        }catch(e: Exception){
            println(e.message)
            return ResultFactory.getFailResult("unable to register user")
        }

        return ResultFactory.getSuccessResult ("User Successfully Registered")
    }

    override fun RegisterStaffUser(userRequest: RegisterStaffUserRequest) : Result<String> {

        // add check for organization exists in a DB
        if (!organizationRepo.existsByOrgDefault(true)) {
            //return ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST)
            return ResultFactory.getFailResult("No Default Organization")
        }

        // add check for username exists in a DB
        if (usersRepository.existsByUsername(userRequest.email)) {
            //return ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST)
            return ResultFactory.getFailResult("Username already exists.")
        }

        val organization = organizationRepo.findByOrgDefault(true)
        val agnActive = organization.orgStatus.equals("ACTIVE")

        val password = generateRandomAlphanumeric(6)
        //Check password conformance.
        /*
        val validationResult = passwordValidator(userRequest.password)
        if (!validationResult.isValid) {
            val message = validationResult.errors.joinToString(" ")
            return ResultFactory.getFailResult("Invalid password: $message")
        }*/


        try {
            // create user object
            var user = UsersModel(
                username = userRequest.email,
                userFullName = userRequest.userFullName,
                userEmail = userRequest.email,
                userActive = agnActive,
                password = passwordEncoder.encode(password),
                userType = "S"
            )

            user = usersRepository.save(user)

            //Fetch and attach Default Roles
            /*for (r in systemRolesRepository.findByRoleDefault(true)){
            val userRole = UserRolesModel(
                userRolesRlId = r,
                userRoleActive = true,
                userRolesUserId = user
            )
            userRolesRepository.save(userRole)
        }*/

            val role = systemRolesRepository.findByRoleName("STAFF")
                .orElseThrow { Exception("role not found") }
            val userRole = UserRolesModel(
                userRolesRlId = role,
                userRoleActive = true,
                userRolesUserId = user
            )
            userRolesRepository.save(userRole)


            //Save Staff Account
            val orgAcc = OrganizationAccountsModel(
                orgAccOrganizationId = organization,
                orgAccUserId = user,
                orgAccActive = agnActive.toString(),
                agnAccActivationDate = Calendar.getInstance().time
            )
            organizationAccountsRepo.save(orgAcc)

            //Send Email Notification on Password to User.
            val receipent = userRequest.email
            val emailSubject = "Cannon Insurance Provider Portal Password Reset"
            val emailBody: String =
                "<p>Dear ${userRequest.userFullName} </p>. " +
                        "<p>Kindly utilize the Password below to access the Cannon Insurance Provider Portal </p>. " +
                        "<p><b>Reset Code.: ${password}</p></b>" +
                        "<p>Cannon Insurance Limited</p>. "
            notificationResourceHelper.sendSimpleEmail(receipent,emailSubject, emailBody, true)

        }catch(e: Exception){
            return ResultFactory.getFailResult("unable to register user")
        }
        return ResultFactory.getSuccessResult ("User Successfully Registered")
    }

    override fun RegisterClientUser(userRequest: RegisterClientUserRequest) {

        // add check for username exists in a DB
        if (usersRepository.existsByUsername(userRequest.userName)) {
            //return ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST)
            throw Exception("ID Number is already assigned to another user!")
        }

        //Check password conformance.
        val validationResult = passwordValidator(userRequest.password)
        if (!validationResult.isValid) {
            val message = validationResult.errors.joinToString(" ")
            throw IllegalArgumentException("Invalid password: $message")
        }

        var client = clientsRepo.findByClientIdNumber(userRequest.userName)
        if(client == null){
           val tpClient = tpClientsRepo.findByClientIdNumber(userRequest.userName)
            for(t in tpClient){
                client = ClientsModel(
                    clientName = t.clientName,
                    clientEmail = t.clientEmail?:"test@test.com",
                    clientDateOfBirth = t.clientDateOfBirth,
                    clientOtherNames = t.clientOtherNames,
                    clientGender = t.clientGender,
                    clientOccupation = t.clientOccupation,
                    clientEmployer = t.clientEmployer,
                    clientPhysicalAddress = t.clientPhysicalAddress,
                    clientPIN = t.clientPIN,
                    clientIdNumber = t.clientIdNumber,
                    clientTqClientCode = t.clientCode,
                    clientThirdPartySystem = "Y"
                )
                client = clientsRepo.save(client)
            }

        }

        // create user object
        var user = UsersModel(
            username = userRequest.userName,
            userFullName = userRequest.userFullName,
            userEmail = client?.clientEmail,
            userActive = true,
            password = passwordEncoder.encode(userRequest.password),
            userType = "C"
        )

        user = usersRepository.save(user)

        //Fetch and attach Default Roles
        /*for (r in systemRolesRepository.findByRoleDefault(true)){
            val userRole = UserRolesModel(
                userRolesRlId = r,
                userRoleActive = true,
                userRolesUserId = user
            )
            userRolesRepository.save(userRole)
        }*/
        val role = systemRolesRepository.findByRoleName("CLIENT")
            .orElseThrow { Exception("role not found") }
        val userRole = UserRolesModel(
            userRolesRlId = role,
            userRoleActive = true,
            userRolesUserId = user
        )
        userRolesRepository.save(userRole)

        //Save Client Account
        val clientAcc = ClientAccountsModel(
            clntAccClientCode = client,
            clntAccUserId = user,
            clntAccClientActive = true.toString(),
            clntAccClientActivationDate = Calendar.getInstance().time
        )
        clientAccountsRepo.save(clientAcc)

    }

    override fun RegisterTpClientUser(idNumber: String){
        val client = tpClientsRepo.findByClientIdNumber(idNumber)
        for (c in client){
            if(c.clientIdNumber!=null){
                val r = RegisterClientUserRequest(
                    userFullName = "${c.clientOtherNames} ${c.clientName!!}",
                    idNumber = c.clientIdNumber,
                    userName = c.clientIdNumber!!,
                    password = "System@${c.clientIdNumber!!}"
                )
                RegisterClientUser(r)
            }else{
                throw Exception("Client has no id number specified!")
            }
        }
    }

    fun passwordValidator(password: String?): PasswordValidationResult{
        val result = PasswordValidationResult()

        if (password.isNullOrBlank() || password.length < 8) {
            result.addError("Password must be at least 8 characters long.")
        }
        if (password?.none { it.isUpperCase() } == true) {
            result.addError("Password must contain at least one uppercase letter.")
        }
        if (password?.none { it.isLowerCase() } == true) {
            result.addError("Password must contain at least one lowercase letter.")
        }
        if (password?.none { it.isDigit() } == true) {
            result.addError("Password must contain at least one digit.")
        }
        if (password?.none { it in "@$!%*?&" } == true) {
            result.addError("Password must contain at least one special character (@\$!%*?&).")
        }
        return result
    }

    override fun AuthenticateAgentUser(userRequest: AuthenticateUserRequest) {
        // add check for email exists in DB
        if (!agentsRepository.existsByAgentShtDesc(userRequest.userName)) {
            //return ResponseEntity("Email is already taken!", HttpStatus.BAD_REQUEST)
            throw ResourceNotFoundException("Agent does not exist.", userRequest.userName, 0)
        }

        val agent = agentsRepository.findByAgentShtDesc(userRequest.userName)
        if(!agent?.agentStatus.equals("ACTIVE")){
            throw Exception("Agent Account has been de-activated.")
        }

        val auth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userRequest.userName,
                                                                                          userRequest.password))
        SecurityContextHolder.getContext().authentication = auth;
    }

    override fun findSystemUsers(userType: String) : Result<Iterable<BasicUsersView>>{
        val usersList: ArrayList<BasicUsersView> = ArrayList<BasicUsersView>()
        for (user in usersRepository.findByUserType(userType)){
            val userDetails = BasicUsersView(
                userId = user.userId,
                username = user.username,
                userEmail = user.userEmail,
                userFullName = user.userFullName
            )
            if(user.userType == "C"){
                val client = clientAccountsRepo.findByClntAccUserId(user)
                userDetails.tpClientCode = client.clntAccClientCode?.clientTqClientCode
                userDetails.clientCode = client.clntAccClientCode?.clientCode
                userDetails.clientName= client.clntAccClientCode?.clientName+ " "+client.clntAccClientCode?.clientOtherNames
                userDetails.agentCode = 0L
                userDetails.agentName = "DIRECT"
                userDetails.agentId = "DIRECT"
                userDetails.clientDob = client.clntAccClientCode?.clientDateOfBirth
            }else{
                val agent = agentAccountsRepository.findByAgnAccUserId(user)
                userDetails.agentCode = agent?.agnAccAgentId?.agentCode
                userDetails.agentName = agent?.agnAccAgentId?.agentName
                userDetails.agentId = agent?.agnAccAgentId?.agentShtDesc
            }
            usersList.add(userDetails)
        }
        return ResultFactory.getSuccessResult(usersList)
    }

    override fun findUserDetails (username: String?) : UsersView {
        val user: UsersModel = usersRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User not found with username or email: $username") }

        val userDetails = UsersView(
            userId = user.userId,
            username = user.username,
            userEmail = user.userEmail,
            userFullName = user.userFullName
        )

        when (user.userType) {
            "C" -> {
                val client = clientAccountsRepo.findByClntAccUserId(user)
                userDetails.tpClientCode = client.clntAccClientCode?.clientTqClientCode
                userDetails.clientCode = client.clntAccClientCode?.clientCode
                userDetails.clientName =
                    client.clntAccClientCode?.clientName + " " + client.clntAccClientCode?.clientOtherNames
                userDetails.agentCode = 0L
                userDetails.agentName = "DIRECT"
                userDetails.agentId = "DIRECT"
                userDetails.clientDob = client.clntAccClientCode?.clientDateOfBirth
            }
            "A" -> {
                val agent = agentAccountsRepository.findByAgnAccUserId(user)
                userDetails.agentCode = agent?.agnAccAgentId?.agentCode
                userDetails.agentName = agent?.agnAccAgentId?.agentName
                userDetails.agentId = agent?.agnAccAgentId?.agentShtDesc
            }
            "P", "S" -> {
                val org = organizationAccountsRepo.findByOrgAccUserId(user)
                userDetails.orgCode = org?.orgAccOrganizationId?.orgCode
                userDetails.orgName = org?.orgAccOrganizationId?.orgName
            }
        }

        val userRoles = userRolesRepository.findByUserRolesUserId(Optional.of(user))
        val usrRolesList = mutableListOf<String>()
        val moduleList: ArrayList<SystemAreasModel> = ArrayList<SystemAreasModel>()
        val userRolesModuleList: ArrayList<UserRoleModulesView> = ArrayList<UserRoleModulesView>()

        for(u in userRoles){
            //Add role name.
            usrRolesList.add(u.userRolesRlId!!.roleName)
            //Add role module.
            val usrModList = UserRoleModulesView(
                roleName = u.userRolesRlId!!.roleName,
            )

            for(m in systemRoleAreasRepo.findByRoleAreaRole(u.userRolesRlId!!)){
                moduleList.add(m.roleAreaSysArea!!)
            }
            usrModList.roleModules = moduleList
            userRolesModuleList.add(usrModList)
        }
        userDetails.roles = userRolesModuleList
        //userDetails.userRolesView = userRolesModuleList

        return userDetails
    }

    override fun InitiateResetAgentPassword(agentCode: String) : Result<String> {

        val agent = agentsRepository.findByAgentShtDesc(agentCode)

        agent?.let {
            val rand = Random.nextInt(1000, 9999)

            it.agentPasswordReset = "Y"
            it.agentPasswordResetDate = Calendar.getInstance().time
            it.agentPasswordResetCode = rand.toLong()

            agentsRepository.save(agent)

            /*val params = JSONObject()
            params.put("subject", "Geminia Life Agency Portal: Password Reset for Agent : ${agent.agentName}")
            params.put("emailAddress", agent.agentEmail)
            val emailBody: String? =
                "<p>Dear ${agent.agentName} </p>. " +
                        "<p>Kindly input the token number below to reset your password in the Geminia Life agency portal </p>. " +
                        "<p><b>Token No.: ${it.agentPasswordResetCode}</p></b>"
            params.put("text", emailBody)

            notificationResourceHelper.sendEmailNoAttachment(params)*/
            val receipent = agent.agentEmail
            val emailSubject = "Geminia Life Portal: Password Reset for Agent : ${agent.agentName}"
            val emailBody: String =
                "<p>Dear ${agent.agentName} </p>. " +
                        "<p>Kindly input the token number below to reset your password in the Geminia Life portal </p>. " +
                        "<p><b>Reset Code.: ${it.agentPasswordResetCode}</p></b>" +
                        "<p>Geminia Life Assurance Limited</p>. "
            notificationResourceHelper.sendSimpleEmail(receipent!!,emailSubject, emailBody, true)

            return ResultFactory.getSuccessResult("Password successfully reset")

        }?: return ResultFactory.getFailResult("Unable to reset password")
    }

    override fun resetAgentPassword(agentCode: String,
                                    resetCode: Long,
                                    password: String) : Result<String> {

        var status = "S"
        var msg = "Password Successfully reset"
        val agent = agentsRepository.findByAgentShtDesc(agentCode)
        agent?.let {
            //Check if the token provided matches.
            if(agent.agentPasswordResetCode == null){
                status = "F"
                msg = "The reset token has not been generated"
            }else if(resetCode == (agent.agentPasswordResetCode ?: 0.09)){
                //Check password conformance.
                val validationResult = passwordValidator(password)
                if (!validationResult.isValid) {
                    val message = validationResult.errors.joinToString(" ")
                    throw IllegalArgumentException("Invalid password: $message")
                }
                val agnAcc = agentAccountsRepository.findByAgnAccAgentId(agent)
                val user = agnAcc.agnAccUserId
                user!!.password = passwordEncoder.encode(password)
                usersRepository.save(user)

                agent.agentPasswordReset = "N"
                agent.agentPasswordResetDate = null
                agent.agentPasswordResetCode = null

                agentsRepository.save(agent)
            }else{
                status = "F"
                msg = "The reset token provided is incorrect"
            }
        }
        return if(status == "F"){
            ResultFactory.getFailResult(msg)
        }else{
            ResultFactory.getSuccessResult (msg)
        }
    }

    override fun InitiateResetClientPassword(clientIDNumber: String) : Result<String> {

        val client = clientsRepo.findByClientIdNumberAndClientThirdPartySystem(clientIDNumber,"Y")

        client?.let {
            val rand = Random.nextInt(1000, 9999)

            it.clientPasswordReset = "Y"
            it.clientPasswordResetDate = Calendar.getInstance().time
            it.clientPasswordResetCode = rand.toLong()

            clientsRepo.save(client)

            /*val params = JSONObject()
            params.put("subject", "Geminia Life Agency Portal: Password Reset for Agent : ${agent.agentName}")
            params.put("emailAddress", agent.agentEmail)
            val emailBody: String? =
                "<p>Dear ${agent.agentName} </p>. " +
                        "<p>Kindly input the token number below to reset your password in the Geminia Life agency portal </p>. " +
                        "<p><b>Token No.: ${it.agentPasswordResetCode}</p></b>"
            params.put("text", emailBody)

            notificationResourceHelper.sendEmailNoAttachment(params)*/
            val receipent = client.clientEmail
            val emailSubject = "Geminia Life Portal: Password Reset for Client : ${client.clientOtherNames} ${client.clientName}"
            val emailBody: String =
                "Dear ${client.clientOtherNames} ${client.clientName}.\n\nKindly input the token number below to reset your password in the Geminia Life portal\n\nReset Code.: ${it.clientPasswordResetCode}.\n\n Geminia Life Insurance Company."
            notificationResourceHelper.sendSimpleEmail(receipent!!,emailSubject, emailBody, true)

            return ResultFactory.getSuccessResult("Password successfully reset")

        }?: return ResultFactory.getFailResult("Unable to reset password")
    }

    override fun emailSendService(receipent: String): Result<String>{
        val emailBody: String =
            "Dear Client.\n\nKindly input the token number below to reset your password in the Geminia Life portal.\n\nReset Code.: XXXXXX\n\n"
        notificationResourceHelper.sendSimpleEmail(receipent,"Test Email", emailBody, true)
        return ResultFactory.getSuccessResult("Password successfully reset")
    }

    override fun resetClientPassword(clientIDNumber: String,
                                     resetCode: Long,
                                     password: String) : Result<String> {

        var status = "S"
        var msg = "Password Successfully reset"
        val client = clientsRepo.findByClientIdNumberAndClientThirdPartySystem(clientIDNumber,"Y")
        client?.let {
            //Check if the token provided matches.
            if(client.clientPasswordResetCode == null){
                status = "F"
                msg = "The reset token has not been generated"
            }else if(resetCode == (client.clientPasswordResetCode ?: 0.09)){
                //Check password conformance.
                val validationResult = passwordValidator(password)
                if (!validationResult.isValid) {
                    val message = validationResult.errors.joinToString(" ")
                    throw IllegalArgumentException("Invalid password: $message")
                }
                val clntAcc = clientAccountsRepo.findByClntAccClientCode(client)
                val user = clntAcc.clntAccUserId
                user!!.password = passwordEncoder.encode(password)
                usersRepository.save(user)

                client.clientPasswordReset = "N"
                client.clientPasswordResetDate = null
                client.clientPasswordResetCode = null

                clientsRepo.save(client)
            }else{
                status = "F"
                msg = "The reset token provided is incorrect"
            }
        }
        return if(status == "F"){
            ResultFactory.getFailResult(msg)
        }else{
            ResultFactory.getSuccessResult (msg)
        }
    }

    override fun findSystemModules() : Result<Iterable<SystemAreasModel>> {
        return ResultFactory.getSuccessResult(systemAreasRepo.findAll())
    }
    override fun findSystemRoles() : Result<Iterable<SystemRolesModel>> {
        return ResultFactory.getSuccessResult(systemRolesRepository.findAll())
    }

    override fun findSystemRoleAreas(systemRoleId: Long) : Result<Iterable<SystemRoleAreasModel>> {
        return ResultFactory.getSuccessResult(systemRoleAreasRepo.findByRoleAreaRole(systemRolesRepository.findByRoleId(systemRoleId)))
    }

    override fun findUserRoles(userId: Long) : Result<Iterable<UserRolesView>> {
        val userRoles: ArrayList<UserRolesView> = ArrayList()
        for(u in userRolesRepository.findByUserRolesUserId(usersRepository.findByUserId(userId))){
            var userR = UserRolesView(
                userRoleId = u.userRoleId,
                userId = u.userRolesUserId!!.userId,
                userRolesRlId = u.userRolesRlId,
                userRoleActive = u.userRoleActive,
            )
            userRoles.add(userR)
        }
        return ResultFactory.getSuccessResult(userRoles)
    }

    override fun saveSystemRole(systemRole: SystemRolesModel){
        val newSystemRole = systemRolesRepository.save(systemRole)
        /*val sysRoleAreasL: ArrayList<SystemRoleAreasRequest> = ArrayList()
        //Populate System Areas
        for(p in systemAreasRepo.findAll()){
            val sya = SystemRoleAreasRequest(
                roleId = newSystemRole.roleId,
                moduleId = p.sysAreaId,
                roleAreaActive = false,
                roleAreaCreate = false,
                roleAreaUpdate = false,
                roleAreaDelete = false,
                roleAreaRead = false,
            )
            sysRoleAreasL.add(sya)
        }
        assignRolesAreas(sysRoleAreasL)*/
    }

    override fun assignUserRoles(userRoles: Iterable<UserRolesRequest>){
        for(ur in userRoles){
            //Fetch user Details
            val user: UsersModel = usersRepository.findByUserId(ur.userId)
                .orElseThrow { Exception("User not found") }

            //Fetch role Details
            val role: SystemRolesModel = systemRolesRepository.findByRoleId(ur.roleId)
                .orElseThrow { Exception("Role not found") }

            val exists = userRolesRepository.existsByUserRolesUserIdAndUserRolesRlId(user, role)
            if(!exists) {
                val userRole = UserRolesModel(
                    userRolesRlId = role,
                    userRolesUserId = user,
                    userRoleActive = ur.roleActive,
                )
                userRolesRepository.save(userRole)
            }else{
                val usrl = userRolesRepository.findByUserRolesUserIdAndUserRolesRlId(user, role)
                usrl.userRoleActive = ur.roleActive
                userRolesRepository.save(usrl)
            }
        }

    }

    override fun assignRolesAreas(roleAreas: Iterable<SystemRoleAreasRequest>){
        for(ur in roleAreas){
            //Fetch user Details
            val sysAreas: SystemAreasModel = systemAreasRepo.findBySysAreaId(ur.moduleId)
                .orElseThrow { Exception("System Area not found") }

            //Fetch role Details
            val role: SystemRolesModel = systemRolesRepository.findByRoleId(ur.roleId)
                .orElseThrow { Exception("Role not found") }

            val exists = systemRoleAreasRepo.existsByRoleAreaRoleAndRoleAreaSysArea(role, sysAreas)
            if(!exists) {
                val sysRoleArea = SystemRoleAreasModel(
                    roleAreaRole = role,
                    roleAreaSysArea = sysAreas,
                    roleAreaActive = ur.roleAreaActive,
                    roleAreaCreate = ur.roleAreaCreate,
                    roleAreaUpdate = ur.roleAreaUpdate,
                    roleAreaDelete = ur.roleAreaDelete,
                    roleAreaRead = ur.roleAreaRead,
                    roleAreaName = role.roleName
                )
                systemRoleAreasRepo.save(sysRoleArea)
            }else{
                val rla = systemRoleAreasRepo.findByRoleAreaRoleAndRoleAreaSysArea(role, sysAreas)
                rla.roleAreaActive = ur.roleAreaActive
                rla.roleAreaCreate = ur.roleAreaCreate
                rla.roleAreaUpdate = ur.roleAreaUpdate
                rla.roleAreaDelete = ur.roleAreaDelete
                rla.roleAreaRead = ur.roleAreaRead
                systemRoleAreasRepo.save(rla)
            }
        }
    }

    override fun InitiateResetUserPassword(email: String) : Result<String> {

        val client = usersRepository.findByUserEmail(email)

        client?.let {
            val rand = Random.nextInt(1000, 9999)

            it.userPasswordReset = "Y"
            it.userPasswordResetDate = Calendar.getInstance().time
            it.userPasswordResetCode = rand.toLong()

            usersRepository.save(client)

            val receipent = client.userEmail
            val emailSubject = "Cannon Insurance Provider Portal: Password Reset"
            val emailBody: String =
                "Dear ${client.userFullName}.\n\nKindly input the token number below to reset your password in the Cannon Insurance Provider portal\n\nReset Code.: ${it.userPasswordResetCode}.\n\n Cannon Insurance Limited."
            notificationResourceHelper.sendSimpleEmail(receipent!!,emailSubject, emailBody, true)

            return ResultFactory.getSuccessResult("Password successfully reset")

        }?: return ResultFactory.getFailResult("Unable to reset password")
    }

    override fun resetUserPassword(email: String,
                                   resetCode: Long,
                                   password: String) : Result<String> {

        var status = "S"
        var msg = "Password Successfully reset"
        val client = usersRepository.findByUserEmail(email)
        client?.let {
            //Check if the token provided matches.
            if(client.userPasswordResetCode == null){
                status = "F"
                msg = "The reset token has not been generated"
            }else if(resetCode == (client.userPasswordResetCode ?: 0.09)){
                //Check password conformance.
                val validationResult = passwordValidator(password)
                if (!validationResult.isValid) {
                    val message = validationResult.errors.joinToString(" ")
                    throw IllegalArgumentException("Invalid password: $message")
                }
                client.password = passwordEncoder.encode(password)
                client.userPasswordReset = "N"
                client.userPasswordResetDate = null
                client.userPasswordResetCode = null

                usersRepository.save(client)
            }else{
                status = "F"
                msg = "The reset token provided is incorrect"
            }
        }
        return if(status == "F"){
            ResultFactory.getFailResult(msg)
        }else{
            ResultFactory.getSuccessResult (msg)
        }
    }

}