package gateway.main.api_gateway.config.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
    

    @GetMapping("/user")
    public String userPage(Principal principal) {
        return "Hello " + principal.getName();
    }


    @GetMapping("/admin")
    public String adminPage(Principal principal) {
        return "Hello " + principal.getName();
    }

}
