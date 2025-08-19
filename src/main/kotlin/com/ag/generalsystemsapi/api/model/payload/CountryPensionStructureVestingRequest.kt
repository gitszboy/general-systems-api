package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class CountryPensionStructureVestingRequest (
    var countryPensionStVestCode: Long? = null,
    var countryPensionStRuleStructure: Long? = null,
    var countryPensionStVestYearFrom: Long? = null,
    var countryPensionStVestYearTo: Long? = null,
    var countryPensionStVestEmplyrEnt: Double? = null,
    var countryPensionStVestEmplyeEnt: Double? = null,
    var countryPensionStVestWEF: Date? = null,
    var countryPensionStVestWET: Date? = null,
)