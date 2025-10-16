package cart.app.cart_service.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        final Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");
        
        if (resourceAccess == null) {
            return List.of();
        }
        
        // Extract roles from spring-client
        final Map<String, Object> springClient = (Map<String, Object>) resourceAccess.get("spring-client");
        
        if (springClient == null) {
            return List.of();
        }
        
        final List<String> roles = (List<String>) springClient.get("roles");
        
        if (roles == null) {
            return List.of();
        }

        return roles.stream()
                .map(roleName -> "ROLE_" + roleName.toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
