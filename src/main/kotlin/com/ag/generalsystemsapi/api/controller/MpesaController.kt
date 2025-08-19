package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.helpers.MpesaResourceHelper
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mpesa")
@Tag(name = "Mobile Money Controller", description = "Endpoint - This service manages calls relating to mpesa mobile payments")
class MpesaController {

    @Autowired
    lateinit var mpesaResourceHelper: MpesaResourceHelper

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Operation(summary = "Mpesa Callback", description = "Recieve mpesa callback response")
    @RequestMapping(value = ["/callback"], method = [RequestMethod.POST])
    fun receiveCallback(@RequestBody payload: Map<String, Any>): ResponseEntity<String> {
        println("🔔 M-Pesa callback received")

        val json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload)
        println("M-Pesa callback request: $json")

        // Start background processing
        mpesaResourceHelper.processCallback(payload)

        // Respond immediately to Safaricom
        return ResponseEntity.ok("Received")
    }

    /*@Operation(summary = "Mpesa Callback", description = "Recieve mpesa callback response")
    @RequestMapping(value = ["/callback"], method = [RequestMethod.POST])
    fun receiveCallback(@RequestBody payload: Map<String, Any>) {
        println("Original M-Pesa callback received: $payload")

        val json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload)
        println("M-Pesa callback received: $json")

        val body = payload["Body"] as Map<*, *>
        val stkCallback = body["stkCallback"] as Map<*, *>
        val resultCode = stkCallback["ResultCode"] as Int
        val merchantRequestID = stkCallback["MerchantRequestID"] as String
        val checkoutRequestID = stkCallback["CheckoutRequestID"] as String
        val resultDesc = stkCallback["ResultDesc"] as String
        val metadata = stkCallback["CallbackMetadata"] as? Map<*, *>
        val items = metadata?.get("Item") as? List<Map<String, Any>>

        val receipt = items?.find { it["Name"] == "MpesaReceiptNumber" }?.get("Value") as String
        val phone = items?.find { it["Name"] == "PhoneNumber" }?.get("Value") as Long
        val amount = items?.find { it["Name"] == "Amount" }?.get("Value") as Int
        val transactionDate = items?.find { it["Name"] == "TransactionDate" }?.get("Value") as Long

        if (resultCode == 0) {
            println("Payment successful! Receipt: $receipt, Phone: $phone, Amount: $amount")

            // Save to DB or update order status
            iReceiptsService.saveMpesaPaymentCallbackResponse(merchantRequestID,
                                                             checkoutRequestID,
                                                             resultCode,
                                                             resultDesc,
                                                             receipt,
                                                             phone,
                                                             amount,
                                                             transactionDate)
        } else {
            println("Payment failed with code: $resultCode")
        }
    }*/
}