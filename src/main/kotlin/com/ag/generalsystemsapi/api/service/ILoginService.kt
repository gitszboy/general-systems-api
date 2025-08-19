package com.ag.generalsystemsapi.api.service

import com.ag.generalsystemsapi.api.model.AgentsModel
import com.ag.generalsystemsapi.api.model.view.AgentResponse
import com.ag.generalsystemsapi.api.model.view.LoginRequest
import com.ag.generalsystemsapi.api.util.Result

interface ILoginService {
    fun agentAuthentication(loginRequest: LoginRequest) : AgentResponse

    fun findAllAgents(): Iterable<AgentsModel>

    fun populateAgents()
    fun InitiateResetAgentPassword(agentCode: String) : Result<String>

    fun resetAgentPassword(agentCode: String,
                           resetCode: Long,
                           password: String) : Result<String>
}