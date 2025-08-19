package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class CorporateClientRequest (
    var entityCode: Long? = null,
    var clientCode: Long? = null,
    var entityCorporationName: String? = null,
    var entityEmail: String?,
    var entityDateOfBirth: Date?,
    var entityPrimaryTelephone: String? = null,
    var entitySecondaryTelephone: String? = null,
    var entityPhysicalAddress: String? = null,
    var entityPostalAddress: String? = null,
    var entityIDNumber: String? = null,
    var entityPIN: String? = null,
    var entityIndustry: String?  = null,
)