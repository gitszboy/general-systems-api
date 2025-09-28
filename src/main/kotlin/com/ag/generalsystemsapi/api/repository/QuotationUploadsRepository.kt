package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.QuotationRisksModel
import com.ag.generalsystemsapi.api.model.QuotationUploadsModel
import org.springframework.data.jpa.repository.JpaRepository

interface QuotationUploadsRepository : JpaRepository<QuotationUploadsModel, Long> {
    fun findByQuoRiskUploadsRisk(quoRiskUploadsRisk: QuotationRisksModel) : Iterable<QuotationUploadsModel>
}