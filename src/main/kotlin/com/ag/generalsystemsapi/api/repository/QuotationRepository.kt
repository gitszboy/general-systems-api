package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.OrganizationModel
import com.ag.generalsystemsapi.api.model.QuotationModel
import org.springframework.data.jpa.repository.JpaRepository

interface QuotationRepository : JpaRepository<QuotationModel, Long> {

    fun findByQuoteOrganization(organization: OrganizationModel) : Iterable<QuotationModel>
}