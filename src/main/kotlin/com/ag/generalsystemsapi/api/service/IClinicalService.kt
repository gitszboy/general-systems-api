package com.ag.generalsystemsapi.api.service

import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitRequest
import com.ag.generalsystemsapi.api.model.responses.ClinicalVisitResponse
import com.ag.generalsystemsapi.api.util.Result

interface IClinicalService {
    fun startClinicalVisit(visit: ClinicalVisitRequest) : Result<ClinicalVisitResponse>
    fun findClinicalVisits(orgCode: Long, status: String) : Result<Iterable<ClinicalVisitResponse>>
}