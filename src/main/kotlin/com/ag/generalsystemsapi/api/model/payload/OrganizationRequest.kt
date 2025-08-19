package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class OrganizationRequest (
    var orgCode: Long? = null,
    var orgName: String? = null,
    var orgPostalAddress: String?,
    var orgPhysicalAddress: String? = null,
    var orgPrimaryTelephone: String? = null,
    var orgSecondaryTelephone: String? = null,
    var orgMobileTelephone: String? = null,
    var orgPin: String? = null,
    var orgMoto: String? = null,
    var orgWebsite: String? = null,
    var orgSchemeName: String? = null,
    var orgStatus: String? = null,
    var orgActivationDate: Date? = null,
    var orgDeactivationDate: Date? = null,
)