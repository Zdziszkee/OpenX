package me.zdziszkee.openx;

import me.zdziszkee.openx.data.user.User;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

public class OpenX {
    public static void main(String[] args) {
    
        WebClient webClient = WebClient.create("https://fakestoreapi.com/");
        
        final Flux<User> userFlux = webClient.get().uri("/users").retrieve().bodyToFlux(User.class);
        final List<User> list = userFlux.collectList().block();
        
    }
}
