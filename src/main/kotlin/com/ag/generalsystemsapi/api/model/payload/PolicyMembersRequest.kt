package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class PolicyMembersRequest (
    var polMemCode: Long? = null,
    var polMemPolicy: Long? = null,
    var polMemEndorsement: Long? = null,
    var polMemSurname: String? = null,
    var polMemOtherNames: String? = null,
    var polMemGender: String? = null,
    var polMemEmail: String? = null,
    var polMemDateOfBirth: Date? = null,
    var polMemPrimaryTelephone: String? = null,
    var polMemOccupation: String? = null,
    var polMemIDNumber: String? = null,
    var polMemPIN: String? = null,
    var polMemEffectiveDate: Date? = null,
    var polMemDateInactivated: Date? = null,
)