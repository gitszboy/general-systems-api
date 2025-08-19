package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class ProspectsRequest (
    var prospectCode: Long? = null,
    var prospectName: String,
    var prospectDateOfBirth: Date,
    var prospectTelephone: String,
    var prospectEmail: String? = null,
    var prospectIdNumber: String? = null,
    var prospectPhysicalAddress: String? = null,
    var prospectOccupation: String? = null,
)