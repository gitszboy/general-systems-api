package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.AgentsModel
import org.springframework.data.jpa.repository.JpaRepository

interface AgentsRepository : JpaRepository<AgentsModel, Long> {
    fun findByAgentCode(agentCode: Long?) : AgentsModel?

    fun findByAgentShtDesc(agentCode: String?) : AgentsModel?

    fun existsByAgentCode(agentCode: Long): Boolean
    fun existsByAgentShtDesc(agentCode: String): Boolean

}