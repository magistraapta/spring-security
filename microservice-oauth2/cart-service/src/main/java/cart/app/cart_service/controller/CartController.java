package cart.app.cart_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    
    @GetMapping("/index")
    public String index() {
        return "cart service is running";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "admin page from cart_service";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView userPage() {
        return new ModelAndView("index");
    }
 


}
