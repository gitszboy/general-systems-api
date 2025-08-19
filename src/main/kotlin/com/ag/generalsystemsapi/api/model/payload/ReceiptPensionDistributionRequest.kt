package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class ReceiptPensionDistributionRequest (
    var rcptPDCode: Long? = null,
    var rcptPDReceipt: Long? = null,
    var rcptPDPensStructure: Long? = null,
    var rcptPDDate: Date? = null,
    var rcptPDMonth: Long? = null,
    var rcptPDYear: Long? = null,
    var rcptPDType: String? = null,
    var rcptPDDrCr: String? = null,
    var rcptPDStatus: String? = null,
    var rcptPDAllocMode: String? = null,
    var rcptPDEmplyrAmt: Double? = null,
    var rcptPDEmplyeAmt: Double? = null,
    var rcptPDEmplyrRegAmt: Double? = null,
    var rcptPDEmplyeRegAmt: Double? = null,
    var rcptPDEmplyrRegIntAmt: Double? = null,
    var rcptPDEmplyeRegIntAmt: Double? = null,
    var rcptPDEmplyrUnRegAmt: Double? = null,
    var rcptPDEmplyeUnRegAmt: Double? = null,
    var rcptPDEmplyrUnRegIntAmt: Double? = null,
    var rcptPDEmplyeUnRegIntAmt: Double? = null,
)