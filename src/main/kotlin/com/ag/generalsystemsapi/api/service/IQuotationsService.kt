package com.ag.generalsystemsapi.api.service

import com.ag.generalsystemsapi.api.model.payload.QuotationRequest
import com.ag.generalsystemsapi.api.model.view.QuotationSummary
import com.ag.generalsystemsapi.api.util.Result

interface IQuotationsService {
    fun saveQuotation(request: QuotationRequest) : Result<QuotationSummary>
    fun findOrganizationQuotes(orgCode: Long): Result<Iterable<QuotationSummary>>
    fun findQuoteDetails(quoteCode: Long) : Result<QuotationSummary>
}