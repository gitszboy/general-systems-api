package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.payload.ClinicalVisitRequest
import com.ag.generalsystemsapi.api.model.payload.QuotationRequest
import com.ag.generalsystemsapi.api.model.responses.ClinicalVisitResponse
import com.ag.generalsystemsapi.api.model.view.QuotationSummary
import com.ag.generalsystemsapi.api.service.IQuotationsService
import com.ag.generalsystemsapi.api.util.Result
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

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
    fun findOrganizationQuotes(@RequestParam(required = true) orgCode: Long): Result<Iterable<QuotationSummary>> = iQuotationsService.findOrganizationQuotes(orgCode)

    @Operation(summary = "Find Quotation Details", description = "Fetches Quotation Details")
    @GetMapping("/findQuoteDetails", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findQuoteDetails(@RequestParam(required = true) quoteCode: Long): Result<QuotationSummary> = iQuotationsService.findQuoteDetails(quoteCode)

}