package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.OrganizationModel
import com.ag.generalsystemsapi.api.model.ProductsModel
import com.ag.generalsystemsapi.api.model.payload.QuotationRequest
import com.ag.generalsystemsapi.api.model.responses.QuoteResponse
import com.ag.generalsystemsapi.api.model.view.KeyValueView
import com.ag.generalsystemsapi.api.model.view.QuotationSummary
import com.ag.generalsystemsapi.api.service.IOrganizationService
import com.ag.generalsystemsapi.api.service.IProductsService
import com.ag.generalsystemsapi.api.service.IQuotationsService
import com.ag.generalsystemsapi.api.util.Result
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tpapis")
@Tag(name = "APIs Controller", description = "Endpoint - This service manages calls relating to API calls from third parties")
class APIsController {
    @Autowired
    lateinit var iQuotationsService: IQuotationsService

    @Autowired
    lateinit var iProductsService: IProductsService

    @Autowired
    lateinit var iOrganizationService: IOrganizationService

    @Operation(summary = "Find Default Organization", description = "Fetches Default Organization")
    @GetMapping("/findDefaultOrganization", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findDefaultOrganization(): Result<KeyValueView> = iOrganizationService.findDefaultOrganization()

    @Operation(summary = "Find Pet Products", description = "Fetches Pet Products")
    @GetMapping("/findDefaultPetProducts", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findDefaultPetProducts(): Result<KeyValueView> = iProductsService.findDefaultPetProducts()

    @Operation(summary = "Save Direct Quotation", description = "Saves Direct Quotation Details")
    @RequestMapping(value = ["/saveDirectQuote"], method = [RequestMethod.POST])
    fun saveDirectQuote(
        @RequestBody quoteRequest : QuotationRequest
    ): Result<QuoteResponse> {
        return iQuotationsService.saveDirectQuote(quoteRequest)
    }
}