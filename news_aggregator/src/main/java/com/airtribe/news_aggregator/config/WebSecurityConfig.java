package com.airtribe.news_aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF configuration
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/api/**", "/api-v2/**")) // Disable CSRF for H2 and API endpoints
                // Authorization configuration
                .authorizeHttpRequests(authorizedRequests -> authorizedRequests
                        .requestMatchers("/h2-console/**", "/api/**", "/api-v2/**").permitAll()  // Allow access to specific endpoints
                        .anyRequest().authenticated())  // All other requests require authentication
                .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl("/hello_world", true))  // Redirect after successful login
                // H2 console configuration
                .headers(headers -> headers.frameOptions().sameOrigin());  // Allow frames for H2 console

        return http.build();
    }
}

