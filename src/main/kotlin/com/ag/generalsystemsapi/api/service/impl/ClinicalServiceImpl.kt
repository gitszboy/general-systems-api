package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.helpers.ComputationResourceHelper
import com.ag.generalsystemsapi.api.helpers.FileUploadResourceHelper
import com.ag.generalsystemsapi.api.model.*
import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitRequest
import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitServicesRequest
import com.ag.generalsystemsapi.api.model.responses.ClinicalVisitResponse
import com.ag.generalsystemsapi.api.model.view.*
import com.ag.generalsystemsapi.api.repository.*
import com.ag.generalsystemsapi.api.service.IClinicalService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import com.ag.generalsystemsapi.thirdparty.repository.TpActivePetPolicyRisksRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.text.SimpleDateFormat
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

    @Autowired
    lateinit var classPerilsRepo: ClassPerilsRepository

    @Autowired
    lateinit var policyRiskPerilBalancesRepo: PolicyRiskPerilBalancesRepository

    @Autowired
    lateinit var petsScheduleRepo: PetsScheduleRepository

    @Autowired
    lateinit var clinicalVisitClaimsRepo: ClinicalVisitClaimsRepository

    @Autowired
    lateinit var fileUploadResourceHelper: FileUploadResourceHelper

    @Autowired
    lateinit var fileDetailsRepo: FileDetailsRepository

    @Autowired
    lateinit var fileTypesRepo: FileTypesRepository

    @Autowired
    lateinit var policyRiskUploadsRepo: PolicyRiskUploadsRepository

    @Autowired
    lateinit var computationResourceHelper: ComputationResourceHelper

    @Autowired
    lateinit var tpActivePetPolicyRisksRepo: TpActivePetPolicyRisksRepository

    @Transactional
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

    override fun updateClinicalVisit(visit: ClinicalVisitRequest) : Result<ClinicalVisitResponse> {
        var updatedVisit = clinicalVisitRepo.findById(visit.visitCode!!)
            .orElseThrow { Exception("visit not found") }

        updatedVisit.visitComplaints = visit.visitComplaints
        updatedVisit.visitExaminations = visit.visitExaminations
        updatedVisit.visitClinicalDiagnosis = visit.visitClinicalDiagnosis
        updatedVisit.visitFinalDiagnosis = visit.visitFinalDiagnosis
        updatedVisit.visitManagement = visit.visitManagement

        updatedVisit = clinicalVisitRepo.save(updatedVisit)

        return ResultFactory.getSuccessResult(constructPatientSummary(updatedVisit.visitCode!!))
    }

    override fun updateClinicalVisitService(visitCode: Long, service: String) : Result<ClinicalVisitResponse> {
        var updatedVisit = clinicalVisitRepo.findById(visitCode)
            .orElseThrow { Exception("visit not found") }

        updatedVisit.visitCurrentService = service

        updatedVisit = clinicalVisitRepo.save(updatedVisit)

        return ResultFactory.getSuccessResult(constructPatientSummary(updatedVisit.visitCode!!))
    }

    override fun completeClinicalVisit(visitCode: Long, status: String) : Result<ClinicalVisitResponse> {
        var updatedVisit = clinicalVisitRepo.findById(visitCode)
            .orElseThrow { Exception("visit not found") }

        updatedVisit.visitStatus = status

        updatedVisit = clinicalVisitRepo.save(updatedVisit)

        //Create the claim.
        val claim = ClinicalVisitClaimsModel(
            clClaimVisit = updatedVisit,
            clClaimDate = updatedVisit.visitDate,
            clClaimAmount = updatedVisit.visitInsuredAmount,
            clClaimStatus = "Awaiting Submission",
            clClaimOrganization = updatedVisit.visitOrganization,
            clClaimSearchStatus = "PENDING"
        )
        clinicalVisitClaimsRepo.save(claim)

        return ResultFactory.getSuccessResult(constructPatientSummary(updatedVisit.visitCode!!))
    }

    override fun submitClinicalClaim(claimCode: Long) : Result<ClinicalVisitResponse> {
        var updatedClaim = clinicalVisitClaimsRepo.findById(claimCode)
            .orElseThrow { Exception("claim not found") }

        updatedClaim.clClaimStatus = "Submitted to Insurer"
        updatedClaim.clClaimSearchStatus = "SUBMITTED"
        updatedClaim.clClaimAmount = updatedClaim.clClaimVisit?.visitInsuredAmount
        clinicalVisitClaimsRepo.save(updatedClaim)

        return ResultFactory.getSuccessResult(constructPatientSummary(updatedClaim.clClaimVisit?.visitCode!!))
    }

    override fun pushClaim(claimCode: Long){
        var claim = clinicalVisitClaimsRepo.findById(claimCode)
            .orElseThrow { Exception("claim not found") }

        var cptGrpCode: Long? = null
        val oracleFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val formattedClaimDate = oracleFormat.format(claim.clClaimDate)

        for(s in clinicalVisitServicesRepo.findByVisitServVisit(claim.clClaimVisit!!)){
            if((s.visitServInsuredAmt ?: 0.0) > 0.0){
                var tqPeril = tpActivePetPolicyRisksRepo.saveClaimPerils(
                    "A",
                    null,
                    s.visitServPeril?.clPerilCode,
                    "S",
                    s.visitServPeril?.clPerilPerilCode?.perilDescription,
                    s.visitServClaimAmt,
                    s.visitServLimitAmt,
                    "S",
                    null,
                    cptGrpCode,
                    null,
                    null,
                    claim.clClaimVisit!!.visitPatient?.policyRiskPolicyBatchNo?.policyClient?.clientCode,
                    "N",
                    null,
                    s.visitServPeril?.clPerilPerilCode?.perilCode,
                    0L
                )

                val res : Any? = tqPeril
                if(res != null){
                    println("value= $cptGrpCode = $res")
                    cptGrpCode = res.toString().toLong()
                }
            }
        }
        if(cptGrpCode != null){
            var tqClaim = tpActivePetPolicyRisksRepo.saveClaim(
                claim.clClaimVisit?.visitPatient?.policyRiskCode,
                claim.clClaimVisit?.visitPatient?.policyRiskPolicyBatchNo?.policyBatchNo,
                formattedClaimDate,
                formattedClaimDate,
                null, //casCode
                null, //casShtDesc
                "O",
                "MEDICAL CLAIM",
                null,
                "MKIHIRO",
                null,
                null,
                cptGrpCode,
                null,
                "N",
                null,
                "N",
                Calendar.getInstance().time,
                null,
                -2000L,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )

        }
    }

    fun populateClinicalServices(newVisit: ClinicalVisitModel){
        for(s in subClassPerilsMapRepo.findBySclPerilMapSubClassAndSclPerilMapBinder(newVisit.visitPatient?.policyRiskSubClassCode!!, newVisit.visitPatient?.policyRiskBindCode!!)){

            val limitAmt = s.sclPerilMapClassPeril?.let { peril ->
                peril.clPerilMaxClmsAllowed?.times(peril.clPerilLimit ?: 1.0) ?: peril.clPerilLimit ?: 0.0
            } ?: 0.0

            var service = ClinicalVisitServicesModel(
                visitServVisit = newVisit,
                visitServPeril = s.sclPerilMapClassPeril,
                visitServDate = Calendar.getInstance().time,
                visitServLimitAmt = limitAmt,
                visitServLimitPerClaimAmt = s.sclPerilMapClassPeril?.clPerilLimit,
                visitServClaimAmt = 0.0,
                visitServNoOfClaims = 0,
                visitServInsuredAmt = 0.0,
                visitServExcessAmt = 0.0,
                visitServStatus = "Draft"
            )
            service = clinicalVisitServicesRepo.save(service)
            populatePolicyRiskPerilBalances(service)
        }
    }

    fun populatePolicyRiskPerilBalances(visit: ClinicalVisitServicesModel) {
        if(!policyRiskPerilBalancesRepo.existsByPolRskPerBalRiskAndPolRskPerBalPeril(visit.visitServVisit?.visitPatient ,visit.visitServPeril)){

            val balanceAmt = visit.visitServPeril?.let { peril ->
                peril.clPerilMaxClmsAllowed?.times(peril.clPerilLimit ?: 1.0) ?: peril.clPerilLimit ?: 0.0
            } ?: 0.0

            val balance = PolicyRiskPerilBalancesModel(
                polRskPerBalRisk = visit.visitServVisit?.visitPatient,
                polRskPerBalPeril = visit.visitServPeril,
                polRskPerBalLimitPerClaim = visit.visitServPeril?.clPerilLimit,
                polRskPerBalTotalLimit = balanceAmt,
                polRskPerBalMaxClmLimit = visit.visitServPeril?.clPerilLimitPerVisit,
                polRskPerBalBalance = balanceAmt,
                polRskPerBalActualBal = balanceAmt,
                polRskPerBalVirtualBal = balanceAmt,
                polRskPerBalTotalClaims = 0.0,
                polRskPerBalStatus = "Available"
            )
            policyRiskPerilBalancesRepo.save(balance)
        }else{
            val servBal = policyRiskPerilBalancesRepo
                .findByPolRskPerBalRiskAndPolRskPerBalPeril(visit.visitServVisit?.visitPatient, visit.visitServPeril)

            visit.visitServStatus = servBal.polRskPerBalStatus
            clinicalVisitServicesRepo.save(visit)
        }
    }

    @Transactional
    override fun updateClinicalVisitServices(
        services: ClinicalVisitServicesRequest
    ): Result<Iterable<ClinicalVisitServicesView>> {

        val visit = clinicalVisitRepo.findById(services.visitCode)
            .orElseThrow { Exception("visit not found") }

        val models = services.servicesList.mapNotNull { v ->
            val service = clinicalVisitServicesRepo.findById(v.visitServCode!!)
                .orElseThrow { Exception("service not found") }

            val peril = classPerilsRepo.findById(v.visitServPerilCode!!)
                .orElseThrow { Exception("Peril not found") }

            val claimAmt = v.visitServClaimAmt ?: 0.0
            if (claimAmt <= 0.0) return@mapNotNull null


            val servBal = policyRiskPerilBalancesRepo
                .findByPolRskPerBalRiskAndPolRskPerBalPeril(visit.visitPatient, peril)

            //TODO: Reset the balance by adding the previous amount.
            var balance = servBal.polRskPerBalBalance ?: 0.0
            balance = balance.plus(service.visitServInsuredAmt?:0.0)

            val (insurerPayableAmt, excessAmt, status) =
             if(claimAmt > (peril.clPerilLimitPerVisit ?: 0.00)){
                 if ((peril.clPerilLimitPerVisit ?: 0.00) > balance) {
                     Triple(balance, (peril.clPerilLimitPerVisit ?: 0.00) - balance, "Exhausted")
                 }
                 else {
                     Triple((peril.clPerilLimitPerVisit ?: 0.00), 0.0, "Available")
                 }
                 Triple(peril.clPerilLimitPerVisit?:claimAmt, claimAmt - (peril.clPerilLimitPerVisit?:claimAmt), "Exhausted")
            }else{
                 if (claimAmt > balance) {
                     Triple(balance, claimAmt - balance, "Exhausted")
                 }
                 else {
                     Triple(claimAmt, 0.0, "Available")
                 }
             }


            service.apply {
                visitServNoOfClaims = 1
                visitServClaimAmt = claimAmt
                visitServInsuredAmt = insurerPayableAmt
                visitServExcessAmt = excessAmt
            }

            servBal.polRskPerBalBalance = balance.minus(insurerPayableAmt)
            servBal.polRskPerBalStatus = status
            servBal.polRskPerBalTotalClaims = servBal.polRskPerBalTotalClaims?:0.0.plus(1L)
            policyRiskPerilBalancesRepo.save(servBal)

            ClinicalVisitServicesModel(
                visitServCode = v.visitServCode,
                visitServVisit = visit,
                visitServPeril = peril,
                visitServDate = v.visitServDate,
                visitServClaimAmt = claimAmt,
                visitServNoOfClaims = 1,
                visitServInsuredAmt = insurerPayableAmt,
                visitServExcessAmt = excessAmt,
                visitServStatus = status,
                visitServLimitPerClaimAmt = v.visitServLimitPerClaim,
                visitServLimitAmt = v.visitServLimitAmt
            )

            //Update the balance amount.

        }

        clinicalVisitServicesRepo.saveAll(models)

        computeClinicalVisitAmounts(visit)

        return findClinicalVisitServices(services.visitCode)
    }

    fun computeClinicalVisitAmounts(visit: ClinicalVisitModel){
        val claimAmt  = clinicalVisitServicesRepo.sumClaimAmountByVisit(visit.visitCode!!) ?: 0.0
        val insuredAmt = clinicalVisitServicesRepo.sumInsuredAmtByVisit(visit.visitCode!!) ?: 0.0
        val excessAmt  = clinicalVisitServicesRepo.sumExcessAmtByVisit(visit.visitCode!!) ?: 0.0

        visit.visitTotalAmount = claimAmt
        visit.visitInsuredAmount = insuredAmt
        visit.visitExcessAmount = excessAmt
        clinicalVisitRepo.save(visit)
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

    override fun findAllClinicalVisits(status: String) : Result<Iterable<ClinicalVisitResponse>>{

        val visitList = clinicalVisitRepo
            .findByVisitStatus(status)
            .map { constructPatientSummary(it.visitCode!!) }

        return ResultFactory.getSuccessResult(visitList)
    }

    override fun findPatientMedicalHistory(patientId: Long) : Result<Iterable<PatientMedicalHistoryView>>{
        val patient = policyRisksRepo.findById(patientId)
            .orElseThrow { Exception("patient not found") }

        val visitList = clinicalVisitRepo
            .findByVisitPatient(patient)
            .map { PatientMedicalHistoryView(
                        visitCode = it.visitCode,
                        visitDate = it.visitDate,
                        visitComplaints = it.visitComplaints,
                        visitExaminations = it.visitExaminations,
                        visitManagement = it.visitManagement,
                        visitClinicalDiagnosis = it.visitClinicalDiagnosis,
                        visitFinalDiagnosis = it.visitFinalDiagnosis,
                        visitStatus = it.visitStatus,
                    ) }

        return ResultFactory.getSuccessResult(visitList)
    }

    override fun findClinicalVisitSummary(visitCode: Long) : Result<ClinicalVisitResponse>{
        return ResultFactory.getSuccessResult(constructPatientSummary(visitCode))
    }

    override fun findClinicalVisitClaims(orgCode: Long, status: String) : Result<Iterable<ClinicalVisitResponse>>{
        val organization = organizationRepo.findById(orgCode)
            .orElseThrow { Exception("organization not found") }

        return ResultFactory.getSuccessResult(clinicalVisitClaimsRepo.findByClClaimOrganizationAndClClaimSearchStatus(organization, status)
            .map { constructPatientSummary(it.clClaimVisit?.visitCode!!)  })
    }

    fun constructPatientSummary(visitCode: Long): ClinicalVisitResponse {
        val visit = clinicalVisitRepo.findById(visitCode)
            .orElseThrow { Exception("clinical visit not found") }

        val schedule = petsScheduleRepo.findByPetSchIpuCode(visit.visitPatient)
            .orElse(null)

        val claim = clinicalVisitClaimsRepo.findByClClaimVisit(visit)
            .orElse(null)

        return ClinicalVisitResponse(
            visitCode = visit.visitCode,
            visitPatientCode = visit.visitPatient?.policyRiskCode,
            visitPatientID = visit.visitPatient?.policyRiskPropertyID,
            visitPatientName = visit.visitPatient?.policyRiskItemDesc,
            visitPatientCover = visit.visitPatient?.policyRiskCoverType,
            visitPatientCoverWef = visit.visitPatient?.policyRiskWef,
            visitPatientCoverWet = visit.visitPatient?.policyRiskWet,
            visitPatientCoverAmount = visit.visitPatient?.policyRiskValue,
            visitInsuredName = visit.visitPatient?.policyRiskPolicyBatchNo?.policyClient?.clientName,
            visitInsuredTel = visit.visitPatient?.policyRiskPolicyBatchNo?.policyClient?.clientTelephone,
            visitInsuredStatus = visit.visitPatient?.policyRiskPolicyBatchNo?.policyStatus,
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
            petSchCode = schedule?.petSchCode,
            petSchAnimal = schedule?.petSchAnimal,
            petSchBreed = schedule?.petSchBreed,
            petSchGender = schedule?.petSchGender,
            petSchDOB = schedule?.petSchDOB,
            petSchWeight = schedule?.petSchWeight,
            petSchMicrochipped = schedule?.petSchMicrochipped,
            petSchMicroshipNo = schedule?.petSchMicroshipNo,
            petSchVaccinated = schedule?.petSchVaccinated,
            petSchMedicalConditions = schedule?.petSchMedicalConditions,
            petSchSurgeries = schedule?.petSchSurgeries,
            petSchMedications = schedule?.petSchMedications,
            petSchIllnessSign = schedule?.petSchIllnessSign,
            petSchInjureOthers = schedule?.petSchInjureOthers,
            petSchCommercial = schedule?.petSchCommercial,
            petSchComments = schedule?.petSchComments,
            petSchClinicName = schedule?.petSchClinicName,
            petSchClinicTel = schedule?.petSchClinicTel,
            petSchClinicTown = schedule?.petSchClinicTown,
            clClaimCode = claim?.clClaimCode,
            clClaimDate = claim?.clClaimDate,
            clClaimAmount = claim?.clClaimAmount,
            clClaimStatus = claim?.clClaimStatus
        )
    }

    override fun uploadClinicalVisitDocument(visitCode: Long, fileTypeCode: Long, file: MultipartFile) : Result<Iterable<PolicyRiskUploadsView>>{
        val visit = clinicalVisitRepo.findById(visitCode)
            .orElseThrow { Exception("clinical visit not found") }

        val fileType = fileTypesRepo.findById(fileTypeCode)
            .orElseThrow { Exception("File Type not found") }

        //Upload File.
        var uplFile = fileUploadResourceHelper.uploadFile(file, "claimDocuments", visit.visitPatient?.policyRiskPropertyID!!)
        uplFile = fileDetailsRepo.save(uplFile)

        //Add upload tracker.
        val upl = PolicyRiskUploadsModel(
            polRiskUploadsCode = null,
            polRiskUploadsRisk = visit.visitPatient,
            polRiskUploadsVisit = visit,
            polRiskUploadDate = Calendar.getInstance().time,
            polRiskUploadsFileDetails = uplFile,
            polRiskUploadsFileType = fileType
        )
        policyRiskUploadsRepo.save(upl)

        val uploadList = policyRiskUploadsRepo
            .findByPolRiskUploadsVisit(visit)
            .map { PolicyRiskUploadsView(
                polRiskUploadsCode = it.polRiskUploadsCode,
                polRiskUploadsVisit = it.polRiskUploadsVisit?.visitCode,
                fileTypeName = it.polRiskUploadsFileType?.fileTypeName,
                fileUploadFileName = it.polRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileUri = it.polRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileDownloadUri = it.polRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileSize = it.polRiskUploadsFileDetails?.fileUploadFileSize,
                fileUploadDate = it.polRiskUploadsFileDetails?.fileUploadDate,
            ) }
        return ResultFactory.getSuccessResult(uploadList)
    }

    override fun findClinicalVisitDocuments(visitCode: Long) : Result<Iterable<PolicyRiskUploadsView>>{
        val visit = clinicalVisitRepo.findById(visitCode)
            .orElseThrow { Exception("clinical visit not found") }

        val uploadList = policyRiskUploadsRepo
                .findByPolRiskUploadsVisit(visit)
            .map { PolicyRiskUploadsView(
                polRiskUploadsCode = it.polRiskUploadsCode,
                polRiskUploadsVisit = it.polRiskUploadsVisit?.visitCode,
                fileTypeName = it.polRiskUploadsFileType?.fileTypeName,
                fileUploadFileName = it.polRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileUri = it.polRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileDownloadUri = it.polRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileSize = it.polRiskUploadsFileDetails?.fileUploadFileSize,
                fileUploadDate = it.polRiskUploadsFileDetails?.fileUploadDate,
            ) }

        return ResultFactory.getSuccessResult(uploadList)
    }

    override fun findClinicalFileTypes() : Result<Iterable<FileTypesModel>>{
        return ResultFactory.getSuccessResult(fileTypesRepo.findByFileTypeArea("C"))
    }

    override fun findOrgDashboardClinicalStats(orgCode: Long) : Result<PetDashboardView>{
        val organization = organizationRepo.findById(orgCode)
            .orElseThrow { Exception("organization not found") }

        val (firstDate, lastDate) = computationResourceHelper.getFirstAndLastDate(Calendar.getInstance().time)
        var cal = Calendar.getInstance()

        //Fetch Claims Count by Paid Status for the month.
        val mnthPaidCnt = clinicalVisitClaimsRepo.countByClClaimDateBetweenAndClClaimPaidStatusAndClClaimOrganization(firstDate, lastDate, "Paid", organization)
        val mnthPendingCnt = clinicalVisitClaimsRepo.countByClClaimDateBetweenAndClClaimPaidStatusAndClClaimOrganization(firstDate, lastDate, "Pending", organization)

        //Fetch Claims Count by Paid Status for the full duration.
        val paidCnt = clinicalVisitClaimsRepo.countByClClaimPaidStatusAndClClaimOrganization("Paid", organization)
        val pendingCnt = clinicalVisitClaimsRepo.countByClClaimPaidStatusAndClClaimOrganization("Pending", organization)

        //Fetch Visits by Status for the month.
        val mnthActiveVisits = clinicalVisitRepo.countByVisitDateBetweenAndVisitOrganizationAndVisitStatus(firstDate, lastDate, organization, "ACTIVE")
        val mnthCompletedVisits = clinicalVisitRepo.countByVisitDateBetweenAndVisitOrganizationAndVisitStatus(firstDate, lastDate, organization, "COMPLETE")

        val value1List = mutableListOf<Long>()
        val value2List = mutableListOf<Long>()
        val value3List = mutableListOf<String>()

        val baseCal = Calendar.getInstance()
        for (c in 1..6) {
            val cal = baseCal.clone() as Calendar
            cal.add(Calendar.MONTH, -c)
            val formatter = SimpleDateFormat("MMM-yy", Locale.ENGLISH)
            value3List.add(formatter.format(firstDate).uppercase())

            val dt = cal.time
            val (firstDate, lastDate) = computationResourceHelper.getFirstAndLastDate(dt)

            val prdPaidCnt = clinicalVisitClaimsRepo.countByClClaimDateBetweenAndClClaimPaidStatusAndClClaimOrganization(
                firstDate, lastDate, "Paid", organization
            )

            val prdPendingCnt = clinicalVisitClaimsRepo.countByClClaimDateBetweenAndClClaimPaidStatusAndClClaimOrganization(
                firstDate, lastDate, "Pending", organization
            )

            value1List.add(prdPaidCnt)      // store paid
            value2List.add(prdPendingCnt)   // store pending
        }

        //Create Claims Bar ChartView
        val claimsBarChart = BarChartView(
            value1 = ChartViewElements("Claims Paid", value1List),
            value2 = ChartViewElements("Claims Pending", value2List),
            axisValue = ChartViewAxis(value3List)
        )

        //Create Claims Pie Chart View
        val claimsPieChart = PieChartView(
            categories = mutableListOf("Claims Paid", "Claims Pending"),
            values = mutableListOf(paidCnt, pendingCnt)
        )

        val result = PetDashboardView(
             monthPaidClaimsCount = mnthPaidCnt,
             monthPendingClaimsCount = mnthPendingCnt,
             allPaidClaimsCount = paidCnt,
             allPendingClaimsCount = pendingCnt,
             monthActiveVisitsCount = mnthActiveVisits,
             monthCompletedVisitsCount = mnthCompletedVisits,
             allActiveVisitsCount = null,
             allCompletedVisitsCount = null,
             barChartView = claimsBarChart,
             pieChartView = claimsPieChart
        )

        return ResultFactory.getSuccessResult(result)
    }
}