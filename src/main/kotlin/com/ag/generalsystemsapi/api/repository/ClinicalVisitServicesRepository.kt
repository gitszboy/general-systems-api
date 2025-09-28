package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClinicalVisitModel
import com.ag.generalsystemsapi.api.model.ClinicalVisitServicesModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ClinicalVisitServicesRepository : JpaRepository<ClinicalVisitServicesModel, Long> {

    fun findByVisitServVisit(visitServVisit: ClinicalVisitModel) : Iterable<ClinicalVisitServicesModel>
    @Query("SELECT SUM(c.visitServClaimAmt) FROM ClinicalVisitServicesModel c WHERE c.visitServVisit.id = :visitCode")
    fun sumClaimAmountByVisit(@Param("visitCode") visitCode: Long): Double?
    @Query("SELECT SUM(c.visitServInsuredAmt) FROM ClinicalVisitServicesModel c WHERE c.visitServVisit.id = :visitCode")
    fun sumInsuredAmtByVisit(@Param("visitCode") visitCode: Long): Double?
    @Query("SELECT SUM(c.visitServExcessAmt) FROM ClinicalVisitServicesModel c WHERE c.visitServVisit.id = :visitCode")
    fun sumExcessAmtByVisit(@Param("visitCode") visitCode: Long): Double?
}