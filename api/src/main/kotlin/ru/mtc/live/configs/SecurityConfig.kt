package ru.mtc.live.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity.csrf { it.disable() }
            .cors { corsCustomizer ->
                corsCustomizer.configurationSource(configurationSource())
            }
            .httpBasic { it.disable() }
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { config ->
                config.anyRequest().permitAll()
            }
            .anonymous { it.disable() }
            .build()
    }

    @Bean
    fun configurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}