package com.ag.generalsystemsapi.api.model.view

import com.ag.generalsystemsapi.api.model.*
import java.util.*
import javax.persistence.*

class QuotationRisksSummary (
    var quoteRiskCode: Long? = null,
    var quoteRiskQuotation: Long? = null,
    var quoteRiskProspectPet: ProspectPetsModel? = null,
    var quoteRiskBindCode: Long? = null,
    var quoteRiskBindName: String? = null,
    var quoteRiskWef: Date? = null,
    var quoteRiskWet: Date? = null,
    var quoteRiskValue: Double? = null,
    var quoteRiskPremium: Double? = null,
    var quoteRiskStatus: String? = null,
)