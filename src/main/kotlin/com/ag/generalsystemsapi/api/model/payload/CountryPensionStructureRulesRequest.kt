package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class CountryPensionStructureRulesRequest (
    var countryPensionStRulesCode: Long? = null,
    var countryPensionStRulesEmplyrContri: Double? = null,
    var countryPensionStRulesEmplyeContri: Double? = null,
    var countryPensionStRulesLEL: Double? = null,
    var countryPensionStRulesUEL: Double? = null,
    var countryPensionStRulesWef: Date? = null,
    var countryPensionStRulesWet: Date? = null,
    var countryPensionStRuleStTiersCode: Long? = null,
)