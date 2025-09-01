package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.model.ClinicalVisitModel
import com.ag.generalsystemsapi.api.model.ClinicalVisitServicesModel
import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitRequest
import com.ag.generalsystemsapi.api.model.responses.ClinicalVisitResponse
import com.ag.generalsystemsapi.api.model.view.ClinicalVisitServicesView
import com.ag.generalsystemsapi.api.repository.*
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

    @Autowired
    lateinit var clinicalVisitServicesRepo: ClinicalVisitServicesRepository

    @Autowired
    lateinit var subClassPerilsMapRepo: SubClassPerilsMapRepository

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

        //populate Services.
        populateClinicalServices(newVisit)

        return ResultFactory.getSuccessResult(constructPatientSummary(newVisit.visitCode!!))
    }

    fun populateClinicalServices(newVisit: ClinicalVisitModel){
        for(s in subClassPerilsMapRepo.findBySclPerilMapSubClassAndSclPerilMapBinder(newVisit.visitPatient?.policyRiskSubClassCode!!, newVisit.visitPatient?.policyRiskBindCode!!)){
            val service = ClinicalVisitServicesModel(
                visitServVisit = newVisit,
                visitServPeril = s.sclPerilMapClassPeril,
                visitServDate = Calendar.getInstance().time,
                visitServLimitAmt = s.sclPerilMapClassPeril?.clPerilLimit,
                visitServClaimAmt = 0.0,
                visitServNoOfClaims = 0,
                visitServInsuredAmt = 0.0,
                visitServExcessAmt = 0.0,
                visitServStatus = "DRAFT"
            )
            clinicalVisitServicesRepo.save(service)
        }
    }

    override fun findClinicalVisitServices(visitCode: Long) : Result<Iterable<ClinicalVisitServicesView>>{
        val visit = clinicalVisitRepo.findById(visitCode)
            .orElseThrow { Exception("visit not found") }
        val servicesL: ArrayList<ClinicalVisitServicesView> = ArrayList()
        for(v in clinicalVisitServicesRepo.findByVisitServVisit(visit)){
            val service = ClinicalVisitServicesView(
                 visitServCode = v.visitServCode ,
                 visitServVisitCode = v.visitServVisit?.visitCode,
                 visitServPerilCode = v.visitServPeril?.clPerilCode,
                 visitServPerilName = v.visitServPeril?.clPerilPerilCode?.perilFullDescription,
                 visitServDate = v.visitServDate,
                 visitServLimitAmt = v.visitServLimitAmt,
                 visitServClaimAmt = v.visitServClaimAmt,
                 visitServNoOfClaims = v.visitServNoOfClaims,
                 visitServInsuredAmt = v.visitServInsuredAmt,
                 visitServExcessAmt = v.visitServExcessAmt,
                 visitServStatus = v.visitServStatus,
            )
            servicesL.add(service)
        }

        return ResultFactory.getSuccessResult(servicesL)
    }

    override fun findClinicalVisits(orgCode: Long, status: String) : Result<Iterable<ClinicalVisitResponse>>{
        val organization = organizationRepo.findById(orgCode)
            .orElseThrow { Exception("organization not found") }

        val visitList = clinicalVisitRepo
            .findByVisitOrganizationAndVisitStatus(organization, status)
            .map { constructPatientSummary(it.visitCode!!) }

        return ResultFactory.getSuccessResult(visitList)
    }

    override fun findClinicalVisitSummary(visitCode: Long) : Result<ClinicalVisitResponse>{
        return ResultFactory.getSuccessResult(constructPatientSummary(visitCode))
    }

    fun constructPatientSummary(visitCode: Long): ClinicalVisitResponse {
        val visit = clinicalVisitRepo.findById(visitCode)
            .orElseThrow { Exception("clinical visit not found") }

        return ClinicalVisitResponse(
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
            visitManagement = visit.visitManagement,
            visitClinicalDiagnosis = visit.visitClinicalDiagnosis,
            visitFinalDiagnosis = visit.visitFinalDiagnosis,
            visitStatus = visit.visitStatus,
        )
    }
}