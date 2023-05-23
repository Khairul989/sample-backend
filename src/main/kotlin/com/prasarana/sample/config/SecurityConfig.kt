package com.prasarana.sample.config

import com.prasarana.sample.service.JwtService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.io.ObjectInputFilter.Config

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtService: JwtService) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        //define public and private route
        http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/api/auth/hello").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
            .requestMatchers("/api/auth/**").authenticated()
            .anyRequest().permitAll()

        //now the best part, yeahahhhhhahaha 2 hari fikir ni woiiiii
        http.oauth2ResourceServer().jwt()
        http.authenticationManager { auth ->
            //get token from bearer token pass in authorization
            val jwt = auth as BearerTokenAuthenticationToken
            //parse the received token
            val user = jwtService.parseToken(jwt.token) ?: throw InvalidBearerTokenException("Invalid Token")
            //Authenticate user with role USER
            UsernamePasswordAuthenticationToken(user, "", listOf(SimpleGrantedAuthority("USER")))
        }

        //other configuration cors
        http.cors()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.csrf().disable()
        http.headers().frameOptions().disable()
        http.headers().xssProtection().disable()

        return http.build()
    }

    //so sebab kat atas dah configure cors untuk disable few thing, ni pulak nak make sure which endpoint
    // bole masuk
    @Bean
    fun corsConfiguration(): CorsConfigurationSource {
        // allow localhost for dev purpose
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000", "http://localhost:8080")
        configuration.allowedHeaders = listOf("authorization", "content-type")
        configuration.allowedMethods = listOf("POST", "PUT", "GET", "DELETE")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}