package com.ag.generalsystemsapi.api.service

import com.ag.generalsystemsapi.api.model.ClinicalVisitClaimsModel
import com.ag.generalsystemsapi.api.model.FileTypesModel
import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitRequest
import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitServicesRequest
import com.ag.generalsystemsapi.api.model.responses.ClinicalVisitResponse
import com.ag.generalsystemsapi.api.model.view.ClinicalVisitServicesView
import com.ag.generalsystemsapi.api.model.view.PatientMedicalHistoryView
import com.ag.generalsystemsapi.api.model.view.PetDashboardView
import com.ag.generalsystemsapi.api.model.view.PolicyRiskUploadsView
import com.ag.generalsystemsapi.api.util.Result
import org.springframework.web.multipart.MultipartFile

interface IClinicalService {
    fun startClinicalVisit(visit: ClinicalVisitRequest) : Result<ClinicalVisitResponse>
    fun findClinicalVisits(orgCode: Long, status: String) : Result<Iterable<ClinicalVisitResponse>>
    fun findClinicalVisitSummary(visitCode: Long) : Result<ClinicalVisitResponse>
    fun findClinicalVisitServices(visitCode: Long) : Result<Iterable<ClinicalVisitServicesView>>
    fun completeClinicalVisit(visitCode: Long, status: String) : Result<ClinicalVisitResponse>
    fun updateClinicalVisitService(visitCode: Long, service: String) : Result<ClinicalVisitResponse>
    fun updateClinicalVisit(visit: ClinicalVisitRequest) : Result<ClinicalVisitResponse>
    fun updateClinicalVisitServices(services: ClinicalVisitServicesRequest): Result<Iterable<ClinicalVisitServicesView>>
    fun findPatientMedicalHistory(patientId: Long) : Result<Iterable<PatientMedicalHistoryView>>
    fun findClinicalVisitClaims(orgCode: Long, status: String) : Result<Iterable<ClinicalVisitResponse>>
    fun submitClinicalClaim(claimCode: Long) : Result<ClinicalVisitResponse>
    fun uploadClinicalVisitDocument(visitCode: Long, fileTypeCode: Long, file: MultipartFile) : Result<Iterable<PolicyRiskUploadsView>>
    fun findClinicalVisitDocuments(visitCode: Long) : Result<Iterable<PolicyRiskUploadsView>>
    fun findClinicalFileTypes() : Result<Iterable<FileTypesModel>>
    fun findAllClinicalVisits(status: String) : Result<Iterable<ClinicalVisitResponse>>
    fun findOrgDashboardClinicalStats(orgCode: Long) : Result<PetDashboardView>
    fun pushClaim(claimCode: Long)
}