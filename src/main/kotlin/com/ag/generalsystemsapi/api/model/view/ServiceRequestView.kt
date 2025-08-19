package com.ag.generalsystemsapi.api.model.view

import com.ag.generalsystemsapi.api.model.ServiceRequestTypesModel
import java.util.*

class ServiceRequestView  (
    var servReqId: Long,
    var servReqType: ServiceRequestTypesModel? = null,
    var servReqUserRequesting: Long? = null,
    var servReqUserRequestingName: String? = null,
    var servReqPolicyCode: Long? = null,
    var servReqPolicyNo: String? = null,
    var servReqAgentCode: Long? = null,
    var servReqAgentName: String? = null,
    var servReqDescription: String?,
    var servReqDate: Date?,
    var servReqAssignee: String?,
    var servReqAssignComments: String?,
    var servReqStatus: String?,
    var servReqUpdateDate: Date? = null,
    var servReqUserAssignedTo: Long? = null,
    var servReqUserAssignedToUserName: String? = null,
    var servReqUserAssignedToName: String? = null,

    )