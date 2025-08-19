package com.ag.generalsystemsapi.api.model.view

import java.util.*

class QuotationSummary (
    var quoteCode: Long?,
    var quoteEffectiveDate: Date?,
    var agentName: String?,
    var productDesc: String?,
    var clientName: String?,
    var clientEmail: String?,
    var clientDateOfBirth: Date?,
    var quoteTerm: Long?,
    var quoteFreqOfPayment: String?,
    var quoteSumAssured: Double?,
    var quotePremium: Double?,
    var quoteTqProposalNo: String?,
    var clientCode: Long?
)