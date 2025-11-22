package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.FileTypesModel
import com.ag.generalsystemsapi.api.model.enums.ClinicalServices
import com.ag.generalsystemsapi.api.model.enums.ClinicalVisitDetails
import com.ag.generalsystemsapi.api.model.enums.ClinicalVisitTypes
import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitRequest
import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitServicesRequest
import com.ag.generalsystemsapi.api.model.responses.ClinicalVisitResponse
import com.ag.generalsystemsapi.api.model.view.ClinicalVisitServicesView
import com.ag.generalsystemsapi.api.model.view.PatientMedicalHistoryView
import com.ag.generalsystemsapi.api.model.view.PetDashboardView
import com.ag.generalsystemsapi.api.model.view.PolicyRiskUploadsView
import com.ag.generalsystemsapi.api.service.IClinicalService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/clinical")
@Tag(name = "Clinic Controller", description = "Endpoint - This service manages calls relating to authentication of Clinic")
@CrossOrigin(origins = ["*"])
class ClinicalController {

    @Autowired
    lateinit var iClinicalService: IClinicalService

    @Operation(summary = "Start Clinical Visits", description = "Save Clinical Visits")
    @RequestMapping(value = ["/startClinicalVisit"], method = [RequestMethod.POST])
    fun startClinicalVisit(
        @RequestBody visit : ClinicalVisitRequest
    ): Result<ClinicalVisitResponse> {
        return iClinicalService.startClinicalVisit(visit)
    }

    @Operation(summary = "Update Clinical Visit", description = "Update Clinical Visits")
    @RequestMapping(value = ["/updateClinicalVisit"], method = [RequestMethod.POST])
    fun updateClinicalVisit(
        @RequestBody visit : ClinicalVisitRequest
    ): Result<ClinicalVisitResponse> {
        return iClinicalService.updateClinicalVisit(visit)
    }

    @Operation(summary = "Update Clinical Visit Service", description = "Update Clinical Visit Service")
    @RequestMapping(value = ["/updateClinicalVisitService"], method = [RequestMethod.POST])
    fun updateClinicalVisitService(
        @RequestParam(name= "visitCode", required = true) visitCode: Long,
        @RequestParam(name= "service", required = true) service: String
    ): Result<ClinicalVisitResponse> {
        return iClinicalService.updateClinicalVisitService(visitCode, service)
    }

    @Operation(summary = "Complete Clinical Visit", description = "Complete Clinical Visit")
    @RequestMapping(value = ["/completeClinicalVisit"], method = [RequestMethod.POST])
    fun completeClinicalVisit(
        @RequestParam(name= "visitCode", required = true) visitCode: Long,
        @RequestParam(name= "status", required = true) status: String
    ): Result<ClinicalVisitResponse> {
        return iClinicalService.completeClinicalVisit(visitCode, status)
    }

    @Operation(summary = "Find Clinical Visits", description = "Fetches Clinical Visits")
    @GetMapping("/findClinicalVisits", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClinicalVisits(@RequestParam(required = true) orgCode: Long, @RequestParam(required = true) status: String): Result<Iterable<ClinicalVisitResponse>> = iClinicalService.findClinicalVisits(orgCode, status)

    @Operation(summary = "Find All Clinical Visits", description = "Fetches Clinical Visits")
    @GetMapping("/findAllClinicalVisits", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllClinicalVisits(@RequestParam(required = true) status: String): Result<Iterable<ClinicalVisitResponse>> = iClinicalService.findAllClinicalVisits(status)

    @Operation(summary = "Find Clinical Visit Types", description = "Fetches Clinical Visit Types")
    @GetMapping("/findClinicalVisitTypes", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClinicalVisitTypes(): Result<Iterable<ClinicalVisitTypes>> = ResultFactory.getSuccessResult(ClinicalVisitTypes.values().toList())

    @Operation(summary = "Find Clinical Visit Details", description = "Fetches Clinical Visit Details")
    @GetMapping("/findClinicalVisitDetails", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClinicalVisitDetails(): Result<Iterable<ClinicalVisitDetails>> = ResultFactory.getSuccessResult(ClinicalVisitDetails.values().toList())

    @Operation(summary = "Find Clinical Services", description = "Fetches Clinical Services")
    @GetMapping("/findClinicalServices", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClinicalServices(): Result<Iterable<ClinicalServices>> = ResultFactory.getSuccessResult(ClinicalServices.values().toList())

    @Operation(summary = "Find Clinical Visit Summary", description = "Fetches Clinical Visit Summary")
    @GetMapping("/findClinicalVisitSummary", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClinicalVisitSummary(@RequestParam(required = true) visitCode: Long): Result<ClinicalVisitResponse> = iClinicalService.findClinicalVisitSummary(visitCode)

    @Operation(summary = "Find Clinical Visit Services", description = "Fetches Clinical Visit Services")
    @GetMapping("/findClinicalVisitServices", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClinicalVisitServices(@RequestParam(required = true) visitCode: Long): Result<Iterable<ClinicalVisitServicesView>> = iClinicalService.findClinicalVisitServices(visitCode)

    @Operation(summary = "Find Patient Medical History", description = "Fetches Patient Medical History")
    @GetMapping("/findPatientMedicalHistory", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPatientMedicalHistory(@RequestParam(required = true) patientCode: Long): Result<Iterable<PatientMedicalHistoryView>> = iClinicalService.findPatientMedicalHistory(patientCode)

    @Operation(summary = "Update Clinical Visit Services", description = "Update Clinical Visit Services")
    @RequestMapping(value = ["/updateClinicalVisitServices"], method = [RequestMethod.POST])
    fun updateClinicalVisitServices(
        @RequestBody service : ClinicalVisitServicesRequest
    ): Result<Iterable<ClinicalVisitServicesView>> {
        return iClinicalService.updateClinicalVisitServices(service)
    }

    @Operation(summary = "Find Clinical Visit Claim", description = "Fetches Clinical Visit Claim")
    @GetMapping("/findClinicalVisitClaims", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClinicalVisitClaims(@RequestParam(required = true) orgCode: Long, @RequestParam(required = true) status: String): Result<Iterable<ClinicalVisitResponse>> = iClinicalService.findClinicalVisitClaims(orgCode, status)

    @Operation(summary = "Submit Clinical Claim", description = "Submit Clinical Claim")
    @RequestMapping(value = ["/submitClinicalClaim"], method = [RequestMethod.POST])
    fun submitClinicalClaim(
        @RequestParam(name= "claimCode", required = true) claimCode: Long
    ): Result<ClinicalVisitResponse> {
        return iClinicalService.submitClinicalClaim(claimCode)
    }

    @Operation(summary = "Upload Clinical Visit Documents", description = "Upload Clinical Visit Documents")
    @RequestMapping(value = ["/uploadClinicalVisitDocument"], method = [RequestMethod.POST])
    fun uploadClinicalVisitDocument(
        @RequestParam(name= "visitCode", required = true) visitCode: Long,
        @RequestParam(name= "fileTypeCode", required = true) fileTypeCode: Long,
        @RequestParam ("file") file: MultipartFile
    ): Result<Iterable<PolicyRiskUploadsView>> {
        return iClinicalService.uploadClinicalVisitDocument(visitCode, fileTypeCode, file)
    }

    @Operation(summary = "find Clinical Visit Documents", description = "find Clinical Visit Documents")
    @GetMapping("/findClinicalVisitDocuments", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClinicalVisitDocuments(
        @RequestParam(name= "visitCode", required = true) visitCode: Long,
    ): Result<Iterable<PolicyRiskUploadsView>> {
        return iClinicalService.findClinicalVisitDocuments(visitCode)
    }

    @Operation(summary = "Find Clinical File Types", description = "Find Clinical File Types")
    @GetMapping("/findClinicalFileTypes", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClinicalFileTypes(
        @RequestParam(name= "visitCode", required = true) visitCode: Long,
    ): Result<Iterable<FileTypesModel>> {
        return iClinicalService.findClinicalFileTypes()
    }

    @Operation(summary = "Find Organization Clinical Stats", description = "Find Organization Clinical Stats")
    @GetMapping("/findOrgDashboardClinicalStats", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findOrgDashboardClinicalStats(
        @RequestParam(name= "orgCode", required = true) orgCode: Long,
    ): Result<PetDashboardView> {
        return iClinicalService.findOrgDashboardClinicalStats(orgCode)
    }

    @Operation(summary = "Push Clinical Claim", description = "Push Clinical Claim")
    @RequestMapping(value = ["/pushClaim"], method = [RequestMethod.POST])
    fun pushClaim(
        @RequestParam(name= "claimCode", required = true) claimCode: Long
    ): ResponseEntity<*> {
         iClinicalService.pushClaim(claimCode)
        return ResponseEntity("Success", HttpStatus.OK)
    }
}