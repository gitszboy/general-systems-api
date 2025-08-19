package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ServiceRequestsModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ServiceRequestsRepository : JpaRepository<ServiceRequestsModel, Long> {

    fun findByServReqClientCode(clientCode: Long?) : Iterable<ServiceRequestsModel>
    fun findByServReqAgentCode(agentCode: Long?) : Iterable<ServiceRequestsModel>
    fun findByServReqPolicyCode(policyCode: Long?) : Iterable<ServiceRequestsModel>
    fun findByServReqId(servReqId: Long) : Optional<ServiceRequestsModel>

}