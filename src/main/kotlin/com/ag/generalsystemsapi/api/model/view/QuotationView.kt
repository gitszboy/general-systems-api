package com.ag.generalsystemsapi.api.model.view


import lombok.Getter
import lombok.Setter
import java.util.*

@Getter
@Setter
class QuotationView (
    var quoteCode: Long?,
    var quoteAgentCode: Long?,
    var prodCode: Long,
    var optionCode: Long?,
    var clientCode: Long?,
    var surname: String?,
    var otherNames: String?,
    var email: String?,
    var dateOfBirth: Date?,
    var term: Long,
    var freqOfPayment: String,
    var paymentMethod: String,
    var sumAssured: Double?,
    var premium: Double?,
    var totalContribution: Double?,
    var retireAge: Long?,
    var benefits : List<Long>,
    var payoutPeriod: Long?,
)