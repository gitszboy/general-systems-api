package com.ag.generalsystemsapi.api.model.payload

import java.util.*
import kotlin.collections.ArrayList

class QuotationRequest (
    var quoteProduct: Long,
    var quoteEffectiveDate: Date,
    var quotePaymentFrequency: String,
    var quotePaymentMethod: String,
    var quoteOrganization: Long,
    var quoteProspect: ProspectsRequest,
    var quoteProspectPets: ArrayList<ProspectPetsRequest>
)