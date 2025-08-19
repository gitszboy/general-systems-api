package com.ag.generalsystemsapi.api.model.view

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import lombok.extern.jackson.Jacksonized

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
class PaymentResponseDetails (
    @JsonProperty("id")
    var id: String?,
    @JsonProperty("shortCode")
    var shortCode: String?,
    @JsonProperty("amount")
    var amount: String?,
    @JsonProperty("msisdn")
    var msisdn: String?,
    @JsonProperty("transactionType")
    var transactionType: String?,
    @JsonProperty("transactionDescription")
    var transactionDescription: String?,
    @JsonProperty("accountReference")
    var accountReference: String?,
    @JsonProperty("transactionDate")
    var transactionDate: String?,
    @JsonProperty("merchantRequestId")
    var merchantRequestId: String?,
    @JsonProperty("checkoutRequestId")
    var checkoutRequestId: String?,
    @JsonProperty("responseCode")
    var responseCode: String?,
    @JsonProperty("responseDescription")
    var responseDescription: String?,
    @JsonProperty("customerMessage")
    var customerMessage: String?,
    @JsonProperty("resultCode")
    var resultCode: String?,
    @JsonProperty("resultDescription")
    var resultDescription: String?,
    @JsonProperty("mpesaReceiptNumber")
    var mpesaReceiptNumber: String?,
    @JsonProperty("serviceType")
    var serviceType: String?,
    @JsonProperty("paymentStatus")
    var paymentStatus: String?,
    @JsonProperty("serviceRequestStatus")
    var serviceRequestStatus: String?,
    @JsonProperty("servicePaymentStatus")
    var servicePaymentStatus: String?,
    @JsonProperty("requestType")
    var requestType: String?,
    @JsonProperty("createdAt")
    var createdAt: String?,
    @JsonProperty("updatedAt")
    var updatedAt: String?,
)