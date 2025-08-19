package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.helpers.NotificationResourceHelper
import com.ag.generalsystemsapi.api.model.AgentsModel
import com.ag.generalsystemsapi.api.model.view.AgentResponse
import com.ag.generalsystemsapi.api.model.view.LoginRequest
import com.ag.generalsystemsapi.api.repository.AgentsRepository
import com.ag.generalsystemsapi.api.service.ILoginService
import com.ag.generalsystemsapi.api.util.ResultFactory
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Calendar
import kotlin.random.Random
import com.ag.generalsystemsapi.api.util.*
import com.ag.generalsystemsapi.thirdparty.repository.TpAgentsRepository

@Service
class LoginServiceImpl : ILoginService {

    @Autowired
    lateinit var tqAgentsRepository: TpAgentsRepository

    @Autowired
    lateinit var agentsRepository: AgentsRepository

    @Autowired
    lateinit var notificationResourceHelper: NotificationResourceHelper


    override fun agentAuthentication(loginRequest: LoginRequest) : AgentResponse{
        var agent = AgentResponse(
            agnCode = null,
            agentName = null,
            agentNo = null,
            agenUserName = null,
            agentLoginMsg = null,
            agentLoginSuccess = 0
        )

        try{
            var response = tqAgentsRepository.authenticateAgent(loginRequest.username, loginRequest.password, "N")

            val msg: Any? = response.get("v_msg")
            val returnVal: Any? = response.get("v_return")
            val agnCode: Any? = response.get("v_agn_code")
            val agnName: Any? = response.get("v_agn_name")

            var agn = AgentsModel(
                agentCode = agnCode.toString().toLong(),
                agentName = agnName.toString(),
                agentShtDesc = loginRequest.username

            )
            agentsRepository.save(agn)

            agent = AgentResponse(
                agnCode = agnCode?.let { agnCode.toString().toLong() } ?: ("0").toLong(),
                agentName = agnName?.let { agnName.toString() },
                agentNo = loginRequest.username,
                agenUserName = loginRequest.username,
                agentLoginMsg = msg?.let { msg.toString() },
                agentLoginSuccess = returnVal?.let { returnVal.toString().toLong() } ?: ("0").toLong(),
            )
        }catch(e: Exception){
            e.printStackTrace()
            ResultFactory.getFailResult<String>("Unable to authenticate agent")
        }
        return agent
    }

    override fun InitiateResetAgentPassword(agentCode: String) : Result<String> {

        val agent = agentsRepository.findByAgentShtDesc(agentCode)

        agent?.let {
            val rand = Random.nextInt(1000, 9999)

            it.agentPasswordReset = "Y"
            it.agentPasswordResetDate = Calendar.getInstance().time
            it.agentPasswordResetCode = rand.toLong()

            agentsRepository.save(agent)

            val params = JSONObject()
            params.put("subject", "Geminia Life Agency Portal: Password Reset for Agent : ${agent.agentName}")
            params.put("emailAddress", agent.agentEmail)
            val emailBody: String? =
                    "<p>Dear ${agent.agentName} </p>. " +
                    "<p>Kindly input the token number below to reset your password in the Geminia Life agency portal </p>. " +
                    "<p><b>Token No.: ${it.agentPasswordResetCode}</p></b>"
            params.put("text", emailBody)

            notificationResourceHelper.sendEmailNoAttachment(params)
            return ResultFactory.getSuccessResult("Password successfully reset")

        }?: return ResultFactory.getFailResult("Unable to reset password")
    }

    override fun resetAgentPassword(agentCode: String,
                                    resetCode: Long,
                                    password: String) : Result<String>{

        var status = "S"
        var msg = "Password Successfully reset"
        val agent = agentsRepository.findByAgentShtDesc(agentCode)
        agent?.let {
            //Check if the token provided matches.
            if(agent.agentPasswordResetCode == null){
                status = "F"
                msg = "The reset token has not been generated"
            }else if(resetCode == (agent.agentPasswordResetCode ?: 0.09)){
                val response = tqAgentsRepository.authenticateAgent(agent.agentShtDesc!!, password, "Y")

                msg = response["v_msg"].toString()
                val returnVal: Any? = response["v_return"]

                if (returnVal == 0){
                    status = "F"
                }else{
                    status = "S"
                    agent.agentPasswordReset = "N"
                    agent.agentPasswordResetDate = null
                    agent.agentPasswordResetCode = null

                    agentsRepository.save(agent)
                    println("return ")
                }
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

    override fun findAllAgents(): Iterable<AgentsModel>{
        return agentsRepository.findAll()
    }

    override fun populateAgents(){
        val agentList : ArrayList<AgentsModel>  = ArrayList()
        for (tqAgent in tqAgentsRepository.findAll()){
                val agent = AgentsModel(
                    agentCode = tqAgent.agnCode,
                    agentShtDesc = tqAgent.agenUserName,
                    agentName = tqAgent.agentName,
                    agentEmail = tqAgent.agentEmail
                )
            agentList.add(agent)
        }
        agentsRepository.saveAll(agentList)
    }

}