package com.ag.generalsystemsapi.config

import com.ag.generalsystemsapi.api.filters.JwtRequestFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration {

    @Autowired
    lateinit var userDetailsService: UserDetailsService

    @Autowired
    lateinit var jwtRequestFilter: JwtRequestFilter

    @Throws(Exception::class)
    protected fun configure(auth: AuthenticationManagerBuilder, http: HttpSecurity) {
        auth.userDetailsService(userDetailsService)
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun authenticationManager (
        configuration: AuthenticationConfiguration
    ): AuthenticationManager? {
        return configuration.authenticationManager
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(
                "/swagger-ui/**",
                "/swagger-ui.html/**",
                "/auth/**",
                "/products/**", //remove on prod,
                "/policies/**", //remove on prod,
                "/pensionStructure/**", //remove on prod,
                "/client/**", //remove on prod,
                "/setups/**", //remove on prod,
                "/quotations/**", //remove on prod,
                "/organizations/**", //remove on prod,
                "/agents/**", //remove on prod,
                "/serviceRequest/**", //remove on prod,
                "/mpesa/**", //remove on prod,
                "/login/**", //remove on prod,
                "/clinical/**", //remove on prod,
                "/receipt/**" //remove on prod,
            ).permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors()

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}