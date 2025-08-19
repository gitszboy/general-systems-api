package com.ag.generalsystemsapi.api.model.payload

class AccountReceiptsAllocRequest (
    var accRcptAllCode: Long? = null,
    var accRcptAllAccountRcpt: Long? = null,
    var accRcptAllEndorsement: Long? = null,
    var accRcptAllAmt: Double?,
    var accRcptAllUserDoneBy: Long? = null,
    var accRcptAllUserDoneDate: Double?,
    var accRcptAllNarration: String?,
    var accRcptAllPosted: String?,
    var accRcptAllUserPostedBy: Long? = null,
    var accRcptAllUserPostedDate: Double? = null,
)