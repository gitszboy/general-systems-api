package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import com.ag.generalsystemsapi.api.model.responses.ClinicalVisitResponse
import com.ag.generalsystemsapi.api.model.responses.PolicyRiskResponse
import com.ag.generalsystemsapi.api.service.IPoliciesService
import com.ag.generalsystemsapi.api.util.Result
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/policies")
@Tag(name = "Policies Controller", description = "Endpoint - This service manages calls relating to Policies")
@CrossOrigin(origins = ["*"])
class PoliciesController {
    @Autowired
    lateinit var iPoliciesService: IPoliciesService

    @Operation(summary = "Populate All Pet Policies", description = "Populate All Pet Policies")
    @RequestMapping(value = ["/populateAllPetPolicies"], method = [RequestMethod.POST])
    fun populateAllPetPolicies(
    ) : ResponseEntity<*> {
        iPoliciesService.populateAllPetPolicies()
        return ResponseEntity("Success", HttpStatus.OK)
    }

    @Operation(summary = "Populate Pet Policy", description = "Populate Pet Policy")
    @RequestMapping(value = ["/populatePetPolicy"], method = [RequestMethod.POST])
    fun populatePetPolicy(@RequestParam(name= "polBatchNo", required = true) polBatchNo: Long) : ResponseEntity<*> {
        iPoliciesService.populatePetPolicy(polBatchNo)
        return ResponseEntity("Success", HttpStatus.OK)
    }

    @Operation(summary = "Find Policy Risks", description = "Fetches Policy Risks")
    @GetMapping("/findPolicyRisks", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPolicyRisks(): Result<Iterable<PolicyRisksModel>> = iPoliciesService.findPolicyRisks()

    @Operation(summary = "Find Policy Risk Details", description = "Fetches Policy Risk Details")
    @GetMapping("/findPolicyRiskDetails", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPolicyRiskDetails(@RequestParam(name= "riskID", required = true) riskID: String): Result<PolicyRiskResponse> = iPoliciesService.findPolicyRiskDetails(riskID)

}