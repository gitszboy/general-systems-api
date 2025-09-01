package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClinicalVisitModel
import com.ag.generalsystemsapi.api.model.ClinicalVisitServicesModel
import org.springframework.data.jpa.repository.JpaRepository

interface ClinicalVisitServicesRepository : JpaRepository<ClinicalVisitServicesModel, Long> {

    fun findByVisitServVisit(visitServVisit: ClinicalVisitModel) : Iterable<ClinicalVisitServicesModel>
}