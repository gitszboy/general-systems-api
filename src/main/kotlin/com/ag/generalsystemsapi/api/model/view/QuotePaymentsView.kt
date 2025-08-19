package com.ag.generalsystemsapi.api.model.view

import java.util.*
class QuotePaymentsView  (
    var quotePayCode: Long?,
    var quotePayRequestDate: Date? = null,
    var quotePayMerchReqId: String? = null,
    var quotePayCheckReqId: String? = null,
    var quotePayReqRespDesc: String? = null,
    var quotePayReqRespCode: String? = null,
    var quotePayReqRespCustMsg: String? = null,
    var quotePayResponseCode: String? = null,
    var quotePayResponseDescription: String? = null,
    var quotePayResultCode: String? = null,
    var quotePayResultDescription: String? = null,
    var quotePayReceiptNumber: String? = null,
    var quotePayTelephone: String? = null,
    var quotePayReceiptAmount: Double? = null,
    var quotePayReceiptDate: Date? = null,
    var quotePayRequestAmount: Double? = null,
    )