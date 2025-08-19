package com.ag.generalsystemsapi.api.helpers

import org.json.JSONObject
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class PaymentResourceHelper {

    //@Value("\${apa.payment-wallet-apis.service.base-url}")
    lateinit var paymentServiceUrl: String

    fun makePaymentRequest(pin:String?,
                           telephone: String?,
                           amount: Double?,
                           reference: String?,
                           description: String?): String?
    {
        val headers = HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        val params = JSONObject()
        //val manualurl = "http://128.4.126.65:8080/payment-wallet/mpesa-express/payment-request"

        params.put("kraPin", pin)
        params.put("customerPhoneNumber", telephone)
        params.put("payableAmount", amount)
        params.put("accountReference", reference)
        params.put("description", description)
        params.put("serviceType", "LIFEAPP")
        params.put("transactionType", "PAYMENT")
        val request: HttpEntity<String> = HttpEntity<String>(params.toString(), headers)

        val response = RestTemplate().postForObject(
            "${paymentServiceUrl}/payment-wallet/mpesa-express/payment-request",
            request,
            String::class.java
        )
        //val response = RestTemplate().postForObject(manualurl, request, String::class.java)

        return response
    }

    fun checkTransactionStatus(requestId: String?): String?{
        val headers = HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        val params = JSONObject()
        //val manualurl = "http://128.4.126.65:8080/payment-wallet/mpesa-express/transaction-details?checkoutRequestId=${requestId}"

        val response = RestTemplate().getForObject("${paymentServiceUrl}/payment-wallet/mpesa-express/transaction-details?checkoutRequestId=${requestId}", String::class.java)
        //val response = RestTemplate().getForObject(manualurl, String::class.java)

        return response
    }
}