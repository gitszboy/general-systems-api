package com.ag.generalsystemsapi.api.model.view

import com.ag.generalsystemsapi.api.model.OrganizationModel
import com.ag.generalsystemsapi.api.model.ProductsModel
import com.ag.generalsystemsapi.api.model.ProspectsModel
import java.util.*

class QuotationSummary (
    var quoteCode: Long?,
    var quoteEffectiveDate: Date?,
    var quoteProduct: ProductsModel?,
    var quoteProspect: ProspectsModel?,
    var quoteOrganization: OrganizationModel?,
    var quoteFreqOfPayment: String?,
    var quoteTerm: Long? = null,
    var quoteSumAssured: Double? = null,
    var quotePremium: Double?,
    var quoteModeOfPayment: String?,
    var quoteCoverFromDate: Date?,
    var quoteCoverToDate: Date?,
    var quoteStatus: String?,
    var quoteRisks: List<QuotationRisksSummary>
)