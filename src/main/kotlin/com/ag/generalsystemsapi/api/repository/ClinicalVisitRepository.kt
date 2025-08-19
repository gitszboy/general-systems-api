package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClinicalVisitModel
import com.ag.generalsystemsapi.api.model.OrganizationModel
import org.springframework.data.jpa.repository.JpaRepository

interface ClinicalVisitRepository : JpaRepository<ClinicalVisitModel, Long> {

    fun findByVisitOrganizationAndVisitStatus(orgCode: OrganizationModel?, status: String) : Iterable<ClinicalVisitModel>
}