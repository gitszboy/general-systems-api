package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClassPerilsModel
import com.ag.generalsystemsapi.api.model.PolicyRiskPerilBalancesModel
import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import org.springframework.data.jpa.repository.JpaRepository

interface PolicyRiskPerilBalancesRepository : JpaRepository<PolicyRiskPerilBalancesModel, Long> {
    fun existsByPolRskPerBalRiskAndPolRskPerBalPeril(risk: PolicyRisksModel?, peril: ClassPerilsModel?) : Boolean

    fun findByPolRskPerBalRiskAndPolRskPerBalPeril(risk: PolicyRisksModel?, peril: ClassPerilsModel?) : PolicyRiskPerilBalancesModel
}