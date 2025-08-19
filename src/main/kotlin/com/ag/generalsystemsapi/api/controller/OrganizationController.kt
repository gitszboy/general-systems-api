package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.OrganizationModel
import com.ag.generalsystemsapi.api.model.payload.OrganizationRequest
import com.ag.generalsystemsapi.api.service.IOrganizationService
import com.ag.generalsystemsapi.api.util.Result
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/organizations")
@Tag(name = "Organizations Controller", description = "Endpoint - This service manages calls relating to Organizations")
@CrossOrigin(origins = ["*"])
class OrganizationController {

    @Autowired
    lateinit var iOrganizationService: IOrganizationService

    @Operation(summary = "Find All Organizations", description = "Fetches all Organizations")
    @GetMapping("/findAllOrganizations", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllOrganizations(): Result<Iterable<OrganizationModel>> = iOrganizationService.findOrganizations()

    @Operation(summary = "Save Organization", description = "Save Organization Details")
    @RequestMapping(value = ["/saveOrganization"], method = [RequestMethod.POST])
    fun saveOrganization(
        @RequestBody organization : OrganizationRequest
    ): ResponseEntity<Unit> {
        iOrganizationService.saveOrganization(organization)

        return ResponseEntity<Unit>(HttpStatus.OK)
    }
}