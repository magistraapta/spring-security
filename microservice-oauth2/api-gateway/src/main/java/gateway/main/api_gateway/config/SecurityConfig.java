package gateway.main.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http
            .authorizeExchange( exchange -> exchange
                .pathMatchers("/actuator/**").permitAll()
                .pathMatchers("/user").hasRole("USER")
                .pathMatchers("/admin").hasRole("ADMIN")
                .anyExchange().authenticated()
            )
            .oauth2Login( Customizer.withDefaults() )
            .oauth2ResourceServer( Customizer.withDefaults() );

        return http.build();
    }
}
