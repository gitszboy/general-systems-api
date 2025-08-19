package com.ag.generalsystemsapi.api.helpers

import org.json.JSONObject
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class OTPResourceHelper {

   //@Value("\${otp-api-service-url}")
   lateinit var notificationUrl: String

     fun generateOtpRequest(emailAddress:String?, mobileNumber: String?) : String?{

        val headers = HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        val params = JSONObject()
        //val manualurl = "http://128.4.30.80:8080/otp/generate"

        params.put("appCode", "")
        params.put("emailAddress", emailAddress)
        params.put("mobileNumber", mobileNumber)
        params.put("otp", "")
        val request: HttpEntity<String> = HttpEntity<String>(params.toString(), headers)

        val response = RestTemplate().postForObject("${notificationUrl}/otp/generate", request, String::class.java)
        return response
    }

   fun reGenerateOtpRequest(emailAddress:String?, mobileNumber: String?):String?{

      val headers = HttpHeaders()
      headers.setContentType(MediaType.APPLICATION_JSON)

      val params = JSONObject()
      //val manualurl = "http://128.4.30.80:8080/otp/regenerate"

      params.put("appCode", "")
      params.put("emailAddress", emailAddress)
      params.put("mobileNumber", mobileNumber)
      params.put("otp", "")
      val request: HttpEntity<String> = HttpEntity<String>(params.toString(), headers)

      val response = RestTemplate().postForObject("${notificationUrl}/otp/regenerate", request, String::class.java)
      //val response = RestTemplate().postForObject(manualurl, request, String::class.java)
      return response

   }

   fun verifyOtpRequest(emailAddress:String?, mobileNumber: String?, otp: String?):String?{

      val headers = HttpHeaders()
      headers.setContentType(MediaType.APPLICATION_JSON)

      val params = JSONObject()
      //val manualurl = "http://128.4.30.80:8080/otp/validate"

      params.put("appCode", "")
      params.put("emailAddress", emailAddress)
      params.put("mobileNumber", mobileNumber)
      params.put("otp", otp)
      val request: HttpEntity<String> = HttpEntity<String>(params.toString(), headers)

      val response = RestTemplate().postForObject("${notificationUrl}/otp/validate", request, String::class.java)
      //val response = RestTemplate().postForObject(manualurl, request, String::class.java)
      return response
   }
}