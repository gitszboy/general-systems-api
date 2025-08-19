package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class IndividualClientRequest (
    var entityCode: Long? = null,
    var clientCode: Long? = null,
    var entityTitle: String? = null,
    var entitySurname: String?,
    var entityOtherNames: String? = null,
    var entityGender: String? = null,
    var entityEmail: String?,
    var entityDateOfBirth: Date?,
    var entityPrimaryTelephone: String? = null,
    var entitySecondaryTelephone: String? = null,
    var entityPhysicalAddress: String? = null,
    var entityPostalAddress: String? = null,
    var entityIDNumber: String? = null,
    var entityPIN: String? = null,
)