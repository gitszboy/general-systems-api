package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import org.springframework.data.jpa.repository.JpaRepository

interface PolicyRisksRepository : JpaRepository<PolicyRisksModel, Long> {
}