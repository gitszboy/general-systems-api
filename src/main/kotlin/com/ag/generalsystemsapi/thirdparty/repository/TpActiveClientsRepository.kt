package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpActiveClientsModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TpActiveClientsRepository : JpaRepository<TpActiveClientsModel, Long> {
    @Query("SELECT u FROM TpActiveClientsModel u " +
            "WHERE u.clientAgentCode=(:agentCode) ")
    fun findAgentClients(@Param("agentCode") agnCode: Long?) : Iterable<TpActiveClientsModel>

    @Query("SELECT u FROM TpActiveClientsModel u " +
            "WHERE u.clientCode=(:clientCode) ")
    fun findTQClientDetails(@Param("clientCode") clientCode: Long?) : TpActiveClientsModel

    @Query("SELECT COUNT(u) FROM TpActiveClientsModel u " +
            "WHERE u.clientAgentCode=(:agentCode) ")
    fun countTQTotalAgentClients(@Param("agentCode") agnCode: Long?): Long?

    @Query("SELECT COUNT(u) FROM TpActiveClientsModel u " +
            "WHERE u.clientAgentCode=(:agentCode) ")
    fun countTQTotalActiveAgentClients(@Param("agentCode") agnCode: Long?): Long?

    @Query("SELECT COUNT(u) FROM TpActiveClientsModel u " +
            "WHERE u.clientAgentCode=(:agentCode) "+
            "AND u.clientIDNumber IS NOT NULL "+
            "AND u.clientPIN IS NOT NULL")
    fun countTQTotalKYCAgentClients(@Param("agentCode") agnCode: Long?): Long?

}