package com.ag.generalsystemsapi.api.model.payload
import java.util.*

class AccountReceiptsRequest (
    var accRcptCode: Long? = null,
    var accRcptType: String? = null,
    var accRcptAgent: Long? = null,
    var accRcptClient: Long? = null,
    var accRcptChequeNo: String? = null,
    var accRcptDrCr: String? = null,
    var accRcptCommInclusive: String? = null,
    var accRcptAdminFeeInclusive: String? = null,
    var accRcptReceiptNo: String? = null,
    var accRcptReceiptDate: Date? = null,
    var accRcptReceiptAmt: Double? = null,
    var accRcptReceiptAllocAmt: Double? = null,
    var accRcptReceiptBalAmt: Double? = null,
    var accRcptFMSRcptPk: Long? = null,
    var accRcptPayMethod: String? = null,
    var accRcptSource: String? = null,
    var accRcptStatus: String? = null,
    var accRcptRemarks: String? = null,
    var accRcptUserDoneBy: Long? = null,
)