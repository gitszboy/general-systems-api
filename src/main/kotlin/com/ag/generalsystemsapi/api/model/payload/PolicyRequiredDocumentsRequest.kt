package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class PolicyRequiredDocumentsRequest (
    var polReqDocCode: Long? = null,
    var polReqDocDocument: Long? = null,
    var polReqDocPolicy: Long? = null,
    var polReqDocStatus: String? = null,
    var polReqDocNumber: String? = null,
    var polReqDocSubmitDate: Date? = null,
    var polReqDocSubmitBy: Long? = null,
)