package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitRequest
import com.ag.generalsystemsapi.api.model.responses.ClinicalVisitResponse
import com.ag.generalsystemsapi.api.service.IClinicalService
import com.ag.generalsystemsapi.api.util.Result
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @Operation(summary = "Find Clinical Visits", description = "Fetches Clinical Visits")
    @GetMapping("/findClinicalVisits", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClinicalVisits(@RequestParam(required = true) orgCode: Long, @RequestParam(required = true) status: String): Result<Iterable<ClinicalVisitResponse>> = iClinicalService.findClinicalVisits(orgCode, status)

}