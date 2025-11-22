package com.ag.generalsystemsapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.ticketing.tqgisclient.CreateTicketImplService

@Configuration
class SoapClientConfig {
    @Bean
    fun createTicketService(): CreateTicketImplService {
        val wsdlUrl = java.net.URL("http://192.168.190.249:7002/TQGIS/CreateTicketImplService?WSDL")
        return CreateTicketImplService(wsdlUrl)
    }
}