package com.ag.generalsystemsapi.api.helpers

import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import javax.mail.internet.MimeMessage


@Component
class NotificationResourceHelper {

    @Value("\${notification.emailUrl}")
    lateinit var emailNotificationUrl: String

    @Value("\${notification.emailSender}")
    lateinit var emailSender: String

    @Value("\${notification.smsApiKey}")
    lateinit var smsApiKey : String//= "f74089bfa653e45b8a51cfdfff16ac83"

    @Value("\${notification.smsPartnerID}")
    lateinit var smsPartnerID: String// = "5629"

    @Value("\${notification.smsShortcode}")
    lateinit var smsShortcode: String// = "GEMINIA"

    @Value("\${notification.smsUrl}")
    lateinit var smsNotificationUrl: String// = "https://quicksms.advantasms.com/api/services/sendsms/"


    //var notificationUrl: String = "http://10.10.1.111:8080/"

    @Autowired
    lateinit var javaMailSender: JavaMailSender

    fun sendEmail(params: JSONObject){
        val path = "/notification/email/sendEmailAttachmentBytes"
        val headers = HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        val request: HttpEntity<String> = HttpEntity<String>(params.toString(), headers)
        RestTemplate().postForObject(emailNotificationUrl+path, request, String::class.java)

    }

    fun sendEmailNoAttachment(params: JSONObject){
        val path = "/notification/email/sendEmail"
        val headers = HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        val request: HttpEntity<String> = HttpEntity<String>(params.toString(), headers)
        RestTemplate().postForObject(emailNotificationUrl+path, request, String::class.java)

    }

    fun sendSimpleEmail(receipent: String, msgSubject: String, msgBody: String, formatMsg: Boolean?){
        // Try block to check for exceptions
         try {
             //val sender = "life@geminialife.co.ke"

             // Creating a simple mail message
            val mailMessage = SimpleMailMessage()

             if(formatMsg == true){
                 val message: MimeMessage = javaMailSender.createMimeMessage()
                 val helper = MimeMessageHelper(message, true, "UTF-8")
                 helper.setTo(receipent)
                 helper.setSubject(msgSubject)
                 helper.setFrom(emailSender)
                 helper.setText(msgBody, true)  // Set 'true' to indicate HTML

                 javaMailSender.send(message)
             }else{
                 mailMessage.from = emailSender
                 mailMessage.setTo(receipent)
                 mailMessage.text = msgBody
                 mailMessage.subject = msgSubject

                 // Sending the mail
                 javaMailSender.send(mailMessage)
             }


             /*if(receipent != null) {
                 javaMailSender.createMimeMessage()
                 val message: MimeMessage = javaMailSender.createMimeMessage()
                 val helper = MimeMessageHelper(message)
                 helper.setTo(receipent)
                 message.setContent(msgBody!!, "text/html")
                 helper.setSubject("BirthDay")

                 // Sending the mail
                 javaMailSender.send(message)
             }*/

        } // Catch block to handle the exceptions
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendSms(message: String, destination: String): String {
        val url = UriComponentsBuilder.fromHttpUrl(smsNotificationUrl)
            .queryParam("apikey", smsApiKey)
            .queryParam("partnerID", smsPartnerID)
            .queryParam("message", message)
            .queryParam("shortcode", smsShortcode)
            .queryParam("mobile", destination)
            .toUriString()

        return RestTemplate().getForObject(url, String::class.java) ?: "No response"
    }

}