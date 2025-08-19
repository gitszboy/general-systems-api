package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpSystemReportTemplateModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpSystemReportTemplateRepository : JpaRepository<TpSystemReportTemplateModel, Long> {

    fun findByReportTemplReportCode(reportCode: Long) : TpSystemReportTemplateModel
}