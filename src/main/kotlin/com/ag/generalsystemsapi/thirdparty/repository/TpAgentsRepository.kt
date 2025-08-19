package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpAgentsModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface TpAgentsRepository : JpaRepository<TpAgentsModel, Long> {
    @Transactional
    @Procedure(name = "auth_agent")
    fun authenticateAgent(
        @Param("v_entered_user") username: String,
        @Param("v_entered_pwd") password: String,
        @Param("v_reset") reset: String
    ): Map<String, Any>

    fun findByAgnCode(agnCode: Long) : TpAgentsModel

    fun findByAgentStatus(status: String) : Iterable<TpAgentsModel>
}