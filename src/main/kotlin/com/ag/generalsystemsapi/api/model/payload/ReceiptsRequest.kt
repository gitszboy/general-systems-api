package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class ReceiptsRequest(
    var rcptCode: Long? = null,
    var rcptPolicy: Long? = null,
    var rcptEndorsement: Long? = null,
    var rcptDate: Date? = null,
    var rcptChequeNo: String? = null,
    var rcptDrCr: String? = null,
    var rcptCommInclusive: String? = null,
    var rcptAdminFeeInclusive: String? = null,
    var rcptReceiptNo: String? = null,
    var rcptReceiptDate: Date? = null,
    var rcptReceiptAmt: Double? = null,
    var rcptFMSRcptPk: Long? = null,
    var rcptPayMethod: String? = null,
    var rcptSource: String? = null,
    var rcptStatus: String? = null,
    var rcptRemarks: String? = null,
    var rcptUserDoneBy: Long? = null,
)