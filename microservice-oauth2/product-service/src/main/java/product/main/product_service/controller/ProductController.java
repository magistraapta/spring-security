package product.main.product_service.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public String index() {
        return "Running from product service";
    }

    @GetMapping("/admin")
    public String adminPage(Principal principal) {
        return "Running from product service - Admin Page - " + principal.getName();
    }

    @GetMapping("/user")
    public String userPage(Principal principal) {
        return "Running from product service - User Page - " + principal.getName();
    }
    
}
