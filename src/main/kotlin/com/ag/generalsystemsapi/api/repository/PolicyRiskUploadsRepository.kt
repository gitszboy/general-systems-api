package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClinicalVisitModel
import com.ag.generalsystemsapi.api.model.PolicyRiskUploadsModel
import org.springframework.data.jpa.repository.JpaRepository

interface PolicyRiskUploadsRepository : JpaRepository<PolicyRiskUploadsModel, Long> {

    fun findByPolRiskUploadsVisit(polRiskUploadsVisit: ClinicalVisitModel?) : Iterable<PolicyRiskUploadsModel>
}