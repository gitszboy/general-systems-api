package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.FileTypesModel
import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitRequest
import com.ag.generalsystemsapi.api.model.payload.QuotationRequest
import com.ag.generalsystemsapi.api.model.responses.ClinicalVisitResponse
import com.ag.generalsystemsapi.api.model.view.PolicyRiskUploadsView
import com.ag.generalsystemsapi.api.model.view.QuotationRiskUploadsView
import com.ag.generalsystemsapi.api.model.view.QuotationSummary
import com.ag.generalsystemsapi.api.service.IQuotationsService
import com.ag.generalsystemsapi.api.util.Result
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/quotations")
@Tag(name = "Quotations Controller", description = "Endpoint - This service manages calls relating to Quotations")
@CrossOrigin(origins = ["*"])
class QuotationsController {

    @Autowired
    lateinit var iQuotationsService: IQuotationsService

    @Operation(summary = "Save Quotation", description = "Saves Quotation Details")
    @RequestMapping(value = ["/saveQuotation"], method = [RequestMethod.POST])
    fun saveQuotation(
        @RequestBody quoteRequest : QuotationRequest
    ): Result<QuotationSummary> {
        return iQuotationsService.saveQuotation(quoteRequest)
    }

    @Operation(summary = "Find Organization Quotations", description = "Fetches  Organization Quotations")
    @GetMapping("/findOrganizationQuotes", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findOrganizationQuotes(@RequestParam(required = true) orgCode: Long, @RequestParam(required = false) prospectCode: Long?): Result<Iterable<QuotationSummary>> = iQuotationsService.findOrganizationQuotes(orgCode, prospectCode)

    @Operation(summary = "Find Quotation Details", description = "Fetches Quotation Details")
    @GetMapping("/findQuoteDetails", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findQuoteDetails(@RequestParam(required = true) quoteCode: Long): Result<QuotationSummary> = iQuotationsService.findQuoteDetails(quoteCode)

    @Operation(summary = "Upload Quotation Risk Documents", description = "Upload Quotation Risk Documents")
    @RequestMapping(value = ["/uploadQuoteRiskDocument"], method = [RequestMethod.POST])
    fun uploadQuoteRiskDocument(
        @RequestParam(name= "quoRiskCode", required = true) quoRiskCode: Long,
        @RequestParam(name= "fileTypeCode", required = true) fileTypeCode: Long,
        @RequestParam ("file") file: MultipartFile
    ): Result<Iterable<QuotationRiskUploadsView>> {
        return iQuotationsService.uploadQuoteRiskDocument(quoRiskCode, fileTypeCode, file)
    }

    @Operation(summary = "find Quotation Risk Documents", description = "find Quotation Risk Documents")
    @GetMapping("/findQuotationDocuments", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findQuotationDocuments(
        @RequestParam(name= "quoRiskCode", required = true) quoRiskCode: Long,
    ): Result<Iterable<QuotationRiskUploadsView>> {
        return iQuotationsService.findQuotationDocuments(quoRiskCode)
    }

    @Operation(summary = "Find Quotation File Types", description = "Find Quotation File Types")
    @GetMapping("/findQuotationFileTypes", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findQuotationFileTypes(
    ): Result<Iterable<FileTypesModel>> {
        return iQuotationsService.findQuotationFileTypes()
    }

    @Operation(summary = "Create Quotation In Third Party", description = "Saves Create Quotation In Third Party")
    @RequestMapping(value = ["/createPolicyInThirdPartySystem"], method = [RequestMethod.POST])
    fun createPolicyInThirdPartySystem(
        @RequestParam(name= "quoCode", required = true) quoCode: Long,
    ): ResponseEntity<*> {
        iQuotationsService.createPolicyInThirdPartySystem(quoCode)
        return ResponseEntity("Success", HttpStatus.OK)
    }
}