package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class CountryPensionStructureRequest (
    var countryPensionStrucCode: Long? = null,
    var countryPensionStrucName: String? = null,
    var countryPensionStrucDesc: String? = null,
    var countryPensionStrucWef: Date? = null,
    var countryPensionStrucsWet: Date? = null,
    var countryPensionStrucCountry: Long? = null,
)