package com.ag.generalsystemsapi.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(info = io.swagger.v3.oas.annotations.info.Info(title = ""))
class SwaggerConfiguration {

    @Value("\${application-description}")
    private val appDescription: String? = null

    @Value("\${application-version}")
    private val appVersion: String? = null

    @Value("\${organization-email}")
    private val email: String? = null

    @Value("\${organization-name}")
    private val name: String? = null

    @Value("\${organization-website}")
    private val website: String? = null

    @Value("\${organization-title}")
    private val title: String? = null

    @Bean
    fun openApiInit(): OpenAPI? {
        return OpenAPI()
            .info(
                Info().title(title).version(appVersion)
                    .contact(Contact().email(email).name(name).url(website))
                    .description(appDescription)
                    .termsOfService("http://swagger.io/terms/")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
    }
}