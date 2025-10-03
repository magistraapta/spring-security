package com.basic.basic_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class BasicController {

    @GetMapping("/")
    public String getMessage() {
        return "Hello World, this is public endpoint";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminMessage() {
        return "This is protected endpoint";
    }

}
