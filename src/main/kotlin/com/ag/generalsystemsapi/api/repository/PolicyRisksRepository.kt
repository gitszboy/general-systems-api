package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PolicyRisksRepository : JpaRepository<PolicyRisksModel, Long> {

    fun findByPolicyRiskPropertyID(riskID: String) : Optional<PolicyRisksModel>
}