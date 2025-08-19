package com.ag.generalsystemsapi.api.reporting

import lombok.Builder
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@Builder
class QuoteDocumentContext (
    var fulName: String?,
    var email: String?,
    var sumAssured: String?,
    var dateOfBirth: String?,
    var term: String?,
    var freqOfPayment: String?,
    var product: String?,
)
