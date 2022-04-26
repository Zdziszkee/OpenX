package me.zdziszkee.openx.service;

import me.zdziszkee.openx.data.product.Product;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ProductService implements Service<Product>{
    private final WebClient webClient;
    
    public ProductService(WebClient webClient) {
        
        this.webClient = webClient;
    }
    public Mono<Product> find(int id) {
        
        
        return webClient.get().uri("/carts").retrieve().bodyToFlux(Product.class).filter(product -> product.getId() == id).single();
    }
}

