package com.ag.generalsystemsapi.api.service

import com.ag.generalsystemsapi.api.model.FileTypesModel
import com.ag.generalsystemsapi.api.model.payload.QuotationRequest
import com.ag.generalsystemsapi.api.model.responses.QuoteResponse
import com.ag.generalsystemsapi.api.model.view.QuotationRiskUploadsView
import com.ag.generalsystemsapi.api.model.view.QuotationSummary
import com.ag.generalsystemsapi.api.util.Result
import org.springframework.web.multipart.MultipartFile

interface IQuotationsService {
    fun saveQuotation(request: QuotationRequest) : Result<QuotationSummary>
    fun findOrganizationQuotes(orgCode: Long, clientCode: Long?): Result<Iterable<QuotationSummary>>
    fun findQuoteDetails(quoteCode: Long) : Result<QuotationSummary>
    fun findQuotationFileTypes() : Result<Iterable<FileTypesModel>>
    fun findQuotationDocuments(quoRiskCode: Long) : Result<Iterable<QuotationRiskUploadsView>>
    fun uploadQuoteRiskDocument(quoRiskCode: Long, fileTypeCode: Long, file: MultipartFile) : Result<Iterable<QuotationRiskUploadsView>>
    fun saveDirectQuote(request: QuotationRequest) : Result<QuoteResponse>
    fun createPolicyInThirdPartySystem(quoteCode: Long) : Result<String>
}