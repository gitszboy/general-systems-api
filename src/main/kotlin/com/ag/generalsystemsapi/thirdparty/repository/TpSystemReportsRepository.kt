package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpSystemReportsModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpSystemReportsRepository : JpaRepository<TpSystemReportsModel, Long> {
    fun findByReportCode(reportCode: Long) :TpSystemReportsModel?
}