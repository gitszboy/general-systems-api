package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClinicalVisitClaimsModel
import com.ag.generalsystemsapi.api.model.ClinicalVisitModel
import com.ag.generalsystemsapi.api.model.OrganizationModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClinicalVisitClaimsRepository : JpaRepository<ClinicalVisitClaimsModel, Long> {

    fun findByClClaimVisit(visit: ClinicalVisitModel?) : Optional<ClinicalVisitClaimsModel>
    fun countByClClaimDateBetweenAndClClaimPaidStatusAndClClaimOrganization(dateFrom: Date, dateTo: Date, paidStatus: String, org: OrganizationModel) : Long

    fun countByClClaimPaidStatusAndClClaimOrganization(paidStatus: String, org: OrganizationModel) : Long
    fun findByClClaimOrganizationAndClClaimSearchStatus(org: OrganizationModel?, status: String?) : Iterable<ClinicalVisitClaimsModel>
}