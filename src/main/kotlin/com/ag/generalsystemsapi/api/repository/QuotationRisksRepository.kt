package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.QuotationModel
import com.ag.generalsystemsapi.api.model.QuotationRisksModel
import org.springframework.data.jpa.repository.JpaRepository

interface QuotationRisksRepository : JpaRepository<QuotationRisksModel, Long> {

    fun findByQuoteRiskQuotation(quotation: QuotationModel) : Iterable<QuotationRisksModel>
}