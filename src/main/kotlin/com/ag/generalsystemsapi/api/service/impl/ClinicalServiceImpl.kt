package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.model.ClinicalVisitModel
import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitRequest
import com.ag.generalsystemsapi.api.model.responses.ClinicalVisitResponse
import com.ag.generalsystemsapi.api.repository.ClinicalVisitRepository
import com.ag.generalsystemsapi.api.repository.OrganizationRepository
import com.ag.generalsystemsapi.api.repository.PolicyRisksRepository
import com.ag.generalsystemsapi.api.service.IClinicalService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class ClinicalServiceImpl : IClinicalService {

    @Autowired
    lateinit var clinicalVisitRepo: ClinicalVisitRepository

    @Autowired
    lateinit var organizationRepo: OrganizationRepository

    @Autowired
    lateinit var policyRisksRepo: PolicyRisksRepository

    override fun startClinicalVisit(visit: ClinicalVisitRequest) : Result<ClinicalVisitResponse> {
        val patient = policyRisksRepo.findById(visit.visitPatient)
            .orElseThrow { Exception("patient not found") }

        val organization = organizationRepo.findById(visit.visitOrganization)
            .orElseThrow { Exception("organization not found") }

        var newVisit = ClinicalVisitModel(
             visitPatient = patient,
             visitOrganization = organization,
             visitSystemDate = Calendar.getInstance().time,
             visitDate = visit.visitDate,
             visitDetails = visit.visitDetails,
             visitType = visit.visitType,
             visitBillable = visit.visitBillable,
             visitCurrentService = visit.visitCurrentService,
             visitComplaints = visit.visitComplaints,
             visitExaminations = visit.visitExaminations,
             visitClinicalDiagnosis = visit.visitClinicalDiagnosis,
             visitFinalDiagnosis = visit.visitFinalDiagnosis,
             visitStatus = "ACTIVE"
        )
        newVisit = clinicalVisitRepo.save(newVisit)

        return ResultFactory.getSuccessResult(constructPatientSummary(newVisit.visitCode!!))
    }

    override fun findClinicalVisits(orgCode: Long, status: String) : Result<Iterable<ClinicalVisitResponse>>{
        val organization = organizationRepo.findById(orgCode)
            .orElseThrow { Exception("organization not found") }

        val visitList = clinicalVisitRepo
            .findByVisitOrganizationAndVisitStatus(organization, status)
            .map { constructPatientSummary(it.visitCode!!) }

        return ResultFactory.getSuccessResult(visitList)
    }

    fun constructPatientSummary(visitCode: Long) : ClinicalVisitResponse{
        val visit = clinicalVisitRepo.findById(visitCode)
            .orElseThrow { Exception("clinical visit not found") }

        val visitSummary = ClinicalVisitResponse(
                 visitCode = visit.visitCode,
                 visitPatientCode = visit.visitPatient?.policyRiskCode,
                 visitPatientID = visit.visitPatient?.policyRiskPropertyID,
                 visitPatientName = visit.visitPatient?.policyRiskItemDesc,
                 visitPatientCover = visit.visitPatient?.policyRiskCoverType,
                 visitPatientCoverWef = visit.visitPatient?.policyRiskWef,
                 visitPatientCoverWet = visit.visitPatient?.policyRiskWet,
                 visitPatientCoverAmount = visit.visitPatient?.policyRiskValue,
                 visitTotalAmount = visit.visitTotalAmount,
                 visitInsuredAmount = visit.visitInsuredAmount,
                 visitExcessAmount = visit.visitExcessAmount,
                 visitOrganizationCode = visit.visitOrganization?.orgCode,
                 visitOrganizationName = visit.visitOrganization?.orgName,
                 visitDate = visit.visitDate,
                 visitDetails = visit.visitDetails,
                 visitType = visit.visitType,
                 visitBillable = visit.visitBillable,
                 visitCurrentService = visit.visitCurrentService,
                 visitComplaints = visit.visitComplaints,
                 visitExaminations = visit.visitExaminations,
                 visitClinicalDiagnosis = visit.visitClinicalDiagnosis,
                 visitFinalDiagnosis = visit.visitFinalDiagnosis,
                 visitStatus = visit.visitStatus,
        )
        return visitSummary
    }
}