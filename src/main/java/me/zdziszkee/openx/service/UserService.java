package me.zdziszkee.openx.service;

import me.zdziszkee.openx.data.cart.Cart;
import me.zdziszkee.openx.data.user.User;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public class UserService implements Service<User>{
    private final WebClient webClient;
    
    public UserService(WebClient webClient) {
        
        this.webClient = webClient;
    }
    
    public Mono<User> find(int id) {
    
    
        return webClient.get().uri("/users").retrieve().bodyToFlux(User.class).filter(user -> user.getId() == id).single();
    }
}
