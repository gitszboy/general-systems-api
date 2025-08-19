package com.ag.generalsystemsapi.api.model.payload

class ReceiptMemRemittancesRequest (
    var rcptPMRCode: Long? = null,
    var rcptPMRReceipt: Long? = null,
    var rcptPMRMember: Long? = null,
    var rcptPMRMonth: Long? = null,
    var rcptPMRYear: Long? = null,
    var rcptPMREmplyrAmt: Double? = null,
    var rcptPMREmplyeAmt: Double? = null,
    var rcptPMREmplyeVolAmt: Double? = null,
)