package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class QuotePaymentModesRequest (
    var quoteCode: Long,
    var quoteModeOfPayment: String,
    var quoteBankBranch: Long?,
    var quoteBankAccountNo: String?,
    var quoteCheckOffInstitution: Long?,
    var quoteCheckOffPayrollNo: String?,
    var quoteMpesaTelephone: String?,
    var quoteDeductionDate: Date?,
)