package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.AgentAccountsModel
import com.ag.generalsystemsapi.api.model.AgentsModel
import com.ag.generalsystemsapi.api.model.UsersModel
import org.springframework.data.jpa.repository.JpaRepository

interface AgentAccountsRepository : JpaRepository<AgentAccountsModel, Long> {
    fun findByAgnAccUserId(agnAccUserId: UsersModel) : AgentAccountsModel?

    fun findByAgnAccAgentId(agent: AgentsModel?) : AgentAccountsModel
}