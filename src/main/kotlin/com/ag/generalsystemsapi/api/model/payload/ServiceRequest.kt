package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class ServiceRequest (
    var servReqId: Long,
    var servReqType: Long? = null,
    var servReqUserRequesting: Long? = null,
    var servReqClientCode: Long? = null,
    var servReqPolicyCode: Long? = null,
    var servReqAgentCode: Long? = null,
    var servReqDescription: String?,
    var servReqDate: Date?,
    var servReqAssignee: String?,
    var servReqAssignComments: String?,
    var servReqStatus: String?,
    var servReqUpdateDate: Date? = null,

)