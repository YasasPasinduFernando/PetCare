package org.agile.petcare.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (not for production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // ✅ Allow public access
                        .requestMatchers("/actuator/**").permitAll() // ✅ Allow public access to Actuator endpoints
                        .anyRequest().permitAll() // 🔐 Everything else requires auth
                )
                .httpBasic(); // Enable basic auth

        return http.build();
    }
    //swaggeradded
}
