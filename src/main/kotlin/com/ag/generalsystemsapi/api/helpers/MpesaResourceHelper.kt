package com.ag.generalsystemsapi.api.helpers

import com.ag.generalsystemsapi.api.model.responses.MpesaErrorResponse
import com.ag.generalsystemsapi.api.model.responses.StkPushResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class MpesaResourceHelper {

     @Value("\${mobile.consumerKey}")
     lateinit var consumerKey: String

     @Value("\${mobile.consumerSecret}")
     lateinit var consumerSecret: String

     @Value("\${mobile.shortCode}")
     lateinit var shortCode: String

     @Value("\${mobile.passkey}")
     lateinit var passkey: String

     @Value("\${mobile.callbackUrl}")
     lateinit var callbackUrl: String// = "https://yourdomain.com/api/payments/callback"

     @Value("\${mobile.pushUrl}")
     lateinit var pushUrl: String

     @Value("\${mobile.accessTokenUrl}")
     lateinit var accessTokenUrl: String

    //@Autowired
    //lateinit var iReceiptsService: IReceiptsService

    fun getAccessToken(): String {
        val credentials = "$consumerKey:$consumerSecret"
        val encoded = Base64.getEncoder().encodeToString(credentials.toByteArray())

        val headers = HttpHeaders().apply {
            set("Authorization", "Basic $encoded")
        }

        val entity = HttpEntity<String>(headers)
        val response = RestTemplate().exchange(
            accessTokenUrl,
            HttpMethod.GET, entity, Map::class.java
        )

        return response.body?.get("access_token").toString()
    }

    fun generatePassword(shortCode: String, passkey: String, timestamp: String): String {
        val raw = shortCode + passkey + timestamp
        return Base64.getEncoder().encodeToString(raw.toByteArray())
    }

    fun stkPush(phoneNumber: String, amount: Int, reference: String) : StkPushResponse? {
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        val password = generatePassword(shortCode, passkey, timestamp)
        val accessToken = getAccessToken()

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            setBearerAuth(accessToken)
        }

        println("callbackUrl: $callbackUrl")

        val payload = mapOf(
            "BusinessShortCode" to shortCode,
            "Password" to password,
            "Timestamp" to timestamp,
            "TransactionType" to "CustomerPayBillOnline",
            "Amount" to amount,
            "PartyA" to phoneNumber,
            "PartyB" to shortCode,
            "PhoneNumber" to phoneNumber,
            "CallBackURL" to callbackUrl,
            "AccountReference" to reference,
            "TransactionDesc" to "Payment"
        )

        val restTemplate = RestTemplate()

        return try {

            val entity = HttpEntity(payload, headers)
            val response = restTemplate.exchange(
                pushUrl,
                HttpMethod.POST,
                entity,
                StkPushResponse::class.java
            )
            println("✅ STK Push Response: ${response.body}")
            response.body
        } catch (ex: HttpClientErrorException) {
            val errorJson = ex.responseBodyAsString
            val mapper = jacksonObjectMapper()
            val error = mapper.readValue(errorJson, MpesaErrorResponse::class.java)
            println("❌ STK Push Failed: $error")
            null
        }
    }

    @Async
    fun processCallback(payload: Map<String, Any>) {
        try {
            val body = payload["Body"] as? Map<*, *> ?: return
            val stkCallback = body["stkCallback"] as? Map<*, *> ?: return
            val resultCode = stkCallback["ResultCode"] as? Int ?: return
            val merchantRequestID = stkCallback["MerchantRequestID"] as? String ?: return
            val checkoutRequestID = stkCallback["CheckoutRequestID"] as? String ?: return
            val resultDesc = stkCallback["ResultDesc"] as? String ?: return
            val metadata = stkCallback["CallbackMetadata"] as? Map<*, *>
            val items = (metadata?.get("Item") as? List<*>)?.filterIsInstance<Map<String, Any>>()

            val receipt = items?.find { it["Name"] == "MpesaReceiptNumber" }?.get("Value") as? String
            val phone = items?.find { it["Name"] == "PhoneNumber" }?.get("Value") as? Long
            val amount = items?.find { it["Name"] == "Amount" }?.get("Value") as? Double
            val transactionDate = items?.find { it["Name"] == "TransactionDate" }?.get("Value") as? Long

            println("⚠️ Callback received Values: $resultCode $receipt $phone $amount $transactionDate")
            if (resultCode == 0 && receipt != null && phone != null && amount != null && transactionDate != null) {
                // Save or process payment
                /*iReceiptsService.saveMpesaPaymentCallbackResponse(merchantRequestID,
                                                                 checkoutRequestID,
                                                                 resultCode,
                                                                 resultDesc,
                                                                 receipt,
                                                                 phone,
                                                                 amount,
                                                                 transactionDate)*/
                println("✅ Mpesa Payment Receipt Successful")
            } else {
                println("⚠️ Callback received but missing some values")
            }
        } catch (e: Exception) {
            println("❌ Error in async callback processing: ${e.message}")
        }
    }
}