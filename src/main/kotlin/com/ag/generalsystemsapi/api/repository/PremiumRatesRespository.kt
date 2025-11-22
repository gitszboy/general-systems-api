package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.PremiumRatesModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface PremiumRatesRespository : JpaRepository<PremiumRatesModel, Long> {
    @Query(value = "SELECT r FROM PremiumRatesModel r " +
            "WHERE r.rateSubClassCode = (:sclCode) " +
            "AND r.rateBindCode = (:bindCode) " +
            "AND r.rateFreqType = (:payFreq) " +
            "AND r.rateSectCode = (:sectCode) " +
            "AND CAST(:rateWef AS date) BETWEEN CAST(r.rateWef AS date) AND CAST(IFNULL(r.rateWet, :rateWef) AS date) ")
    fun findPremiumRate(@Param("sclCode") subClassCode: Long?,
                        @Param("bindCode") bindCode : Long?,
                        @Param("payFreq") payFreq : String?,
                        @Param("sectCode") sectionCode : Long?,
                        @Param("rateWef") effectiveDate : Date?
    ) : Iterable<PremiumRatesModel>
}