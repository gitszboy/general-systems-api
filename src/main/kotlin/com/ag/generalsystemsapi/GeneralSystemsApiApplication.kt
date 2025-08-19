package com.ag.generalsystemsapi

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.EnableAsync
import java.net.InetAddress
import java.util.*

@SpringBootApplication
@EnableAsync
class GeneralSystemsApiApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
	val logger: Logger = LoggerFactory.getLogger(GeneralSystemsApiApplication::class.java)
	val env = runApplication<GeneralSystemsApiApplication>(*args).environment
	val protocol = "http"
	val serverPort = env.getProperty("server.port")
	//runApplication<LifeSystemsApiApplication>(*args)
	try {
		logger.warn(
			"""
-----------------
-----------------------------------------
	Application '{}' is running! Access URLs:
	swagger: 	{}://localhost:{}/generalsystems{}
	Local: 		{}://localhost:{}/generalsystems/
	External: 	{}://{}:{}/generalsystems/
----------------------------------------------------------""",
			env.getProperty("spring.application.name"),
			protocol,
			serverPort,
			env.getProperty("springdoc.swagger-ui.path"),
			protocol,
			serverPort,
			protocol,
			InetAddress.getLocalHost().hostAddress,
			serverPort,
			env.activeProfiles
		)
	}catch (e: Exception) {
		logger.error(e.message)
	}
}

@Bean
fun corsFilter(): CorsFilter? {
	val source = UrlBasedCorsConfigurationSource()
	val config = CorsConfiguration()
	config.allowCredentials = true
	config.allowedOrigins = Arrays.asList(
		"*"
	)
	config.allowedHeaders = Arrays.asList(
		"Origin",
		"Content-Type",
		"Accept",
		"X-Requested-With",
		"Access-Control-Allow-Headers",
		"Access-Control-Allow-Origin",
		"Access-Control-Allow-Credentials",
		"Access-Control-Allow-Private-Network"
	)
	config.allowedMethods = Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
	source.registerCorsConfiguration("/**", config)
	return CorsFilter(source)
}