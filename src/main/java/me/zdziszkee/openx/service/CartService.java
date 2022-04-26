package me.zdziszkee.openx.service;

import me.zdziszkee.openx.data.cart.Cart;
import me.zdziszkee.openx.data.product.Product;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class CartService implements Service<Cart>{
    private final WebClient webClient;
    
    public CartService(WebClient webClient) {
        
        this.webClient = webClient;
    }
    public Mono<Cart> find(int id) {
        
        
        return webClient.get().uri("/carts").retrieve().bodyToFlux(Cart.class).filter(cart -> cart.getId() == id).single();
    }
}
