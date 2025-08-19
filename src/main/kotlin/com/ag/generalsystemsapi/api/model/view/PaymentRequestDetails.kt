package com.ag.generalsystemsapi.api.model.view

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.*
import lombok.extern.jackson.Jacksonized

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
class PaymentRequestDetails (
    @JsonProperty("merchantRequestId")
    var merchantRequestId: String?,
    @JsonProperty("checkoutRequestId")
    var checkoutRequestId: String?,
    @JsonProperty("responseDescription")
    var responseDescription: String?,
    @JsonProperty("responseCode")
    var responseCode: String?,
    @JsonProperty("customerMessage")
    var customerMessage: String?,
)