package com.ag.generalsystemsapi.api.model.view

import java.util.*

class ClientsMedHistoryRequest (
    var clientMedHCode: Long? = null,
    var clientMedHClientCode: Long? = null,
    var clientMedHAilment: String?,
    var clientMedHAilmentWef: Date?,
    var clientMedHAilmentWet: Date?,
    var clientMedHBodyPart: String? = null,
    var clientMedHHospital: String? = null,
    var clientMedHDoctor: String? = null,
    var clientMedHMedFreq: String? = null,
)