package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClinicalVisitModel
import com.ag.generalsystemsapi.api.model.OrganizationModel
import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClinicalVisitRepository : JpaRepository<ClinicalVisitModel, Long> {

    fun findByVisitOrganizationAndVisitStatus(orgCode: OrganizationModel?, status: String) : Iterable<ClinicalVisitModel>

    fun findByVisitPatient(riskid: PolicyRisksModel) : Iterable<ClinicalVisitModel>

    fun findByVisitStatus(visitStatus: String) : Iterable<ClinicalVisitModel>

    fun countByVisitDateBetweenAndVisitOrganizationAndVisitStatus(dateFrom: Date, dateTo: Date, orgCode: OrganizationModel, status: String) : Long
}