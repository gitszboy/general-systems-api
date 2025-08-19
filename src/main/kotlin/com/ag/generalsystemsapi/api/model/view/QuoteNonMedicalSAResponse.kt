package com.ag.generalsystemsapi.api.model.view

class QuoteNonMedicalSAResponse (
    var quoteNmsoCode: Long?,
    var quoteNmsoQuoteCode: Long? = null,
    var qnmsoYearFrom: Long? = null,
    var qnmsoYearTo: Long? = null,
    var qnmsoAmount: Double? = null,
)