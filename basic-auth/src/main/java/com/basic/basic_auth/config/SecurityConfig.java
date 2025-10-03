package com.basic.basic_auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /* 
        this configuration is used to configure the security for the application
        we are disabling the csrf protection and allowing all requests to the public endpoint
        and authenticated requests to the protected endpoint
        we are also allowing all users to login and logout
        we are also allowing all users to access the public endpoint
        we are also allowing all users to access the protected endpoint
        we are also allowing all users to access the public endpoint
    */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}
