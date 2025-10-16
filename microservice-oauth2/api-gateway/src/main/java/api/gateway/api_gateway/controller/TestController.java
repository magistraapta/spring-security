package api.gateway.api_gateway.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class TestController {
    
    @GetMapping("/")
    public String index(@AuthenticationPrincipal OidcUser principal) {
        return "Hello, " + principal.getEmail() + " " + principal.getIdToken().getTokenValue();
    }

    @GetMapping("/credentials")
    public String credentials(@AuthenticationPrincipal OidcUser principal) {
        return "Hello, " + principal.getUserInfo().getFullName() + " " + principal.getIdToken().getTokenValue();
    }
}
