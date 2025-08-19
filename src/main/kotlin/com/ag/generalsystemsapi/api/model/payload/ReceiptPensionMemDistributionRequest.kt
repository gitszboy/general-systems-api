package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class ReceiptPensionMemDistributionRequest (
    var rcptPMDCode: Long? = null,
    var rcptPMDDistribution: Long? = null,
    var rcptPMDPensTier: Long? = null,
    var rcptPMDPensMember: Long? = null,
    var rcptPMDDate: Date? = null,
    var rcptPMDMonth: Long? = null,
    var rcptPMDYear: Long? = null,
    var rcptPMDType: String? = null,
    var rcptPMDDrCr: String? = null,
    var rcptPMDStatus: String? = null,
    var rcptPMDEmplyrAmt: Double? = null,
    var rcptPMDEmplyeAmt: Double? = null,
    var rcptPMDEmplyrRegAmt: Double? = null,
    var rcptPMDEmplyeRegAmt: Double? = null,
    var rcptPMDEmplyrRegIntAmt: Double? = null,
    var rcptPMDEmplyeRegIntAmt: Double? = null,
    var rcptPMDEmplyrUnRegAmt: Double? = null,
    var rcptPMDEmplyeUnRegAmt: Double? = null,
    var rcptPMDEmplyrUnRegIntAmt: Double? = null,
    var rcptPMDEmplyeUnRegIntAmt: Double? = null,
)