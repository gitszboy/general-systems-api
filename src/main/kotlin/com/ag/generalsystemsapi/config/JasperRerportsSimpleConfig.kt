package com.ag.generalsystemsapi.config

import com.ag.generalsystemsapi.api.reporting.JasperReportExporter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JasperRerportsSimpleConfig {

    @Bean
    fun reportExporter(): JasperReportExporter? {
        return JasperReportExporter()
    }

}