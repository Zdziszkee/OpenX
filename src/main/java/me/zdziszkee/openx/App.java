package me.zdziszkee.openx;

import me.zdziszkee.openx.service.CartService;
import me.zdziszkee.openx.service.ProductService;
import me.zdziszkee.openx.service.UserService;
import org.springframework.web.reactive.function.client.WebClient;

public class App {
    public static void main(String[] args) {
    
        WebClient webClient = WebClient.create("https://fakestoreapi.com/");
    
        final ProductService productService = new ProductService(webClient);
        final UserService userService = new UserService(webClient);
        final CartService cartService = new CartService(webClient,productService,userService);
        
    }
}
