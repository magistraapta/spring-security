package com.auth.keycloak_auth.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String publicEndpoint(@AuthenticationPrincipal OidcUser user, Model model) {
        if (user != null) {
            model.addAttribute("username", user.getFullName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("preferredUsername", user.getPreferredUsername());
        }
        return "home";
    }

    @GetMapping("/private")
    public String privateEndpoint(@AuthenticationPrincipal OidcUser user, Model model) {
        if (user != null) {
            model.addAttribute("username", user.getFullName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("preferredUsername", user.getPreferredUsername());
        }
        return "menu";
    }
    
}
