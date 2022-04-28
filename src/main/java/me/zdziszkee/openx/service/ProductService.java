package me.zdziszkee.openx.service;

import me.zdziszkee.openx.data.product.Product;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private final WebClient webClient;
    
    public ProductService(WebClient webClient) {
        
        this.webClient = webClient;
    }
    
    public Map<Integer, Product> getProducts() {
        
        final Map<Integer, Product> products = webClient.get().uri("/products").retrieve().bodyToFlux(Product.class).collectMap(Product::getId).block();
        if (products == null) {
            return new HashMap<>();
        }
        return products;
    }
    public Set<String> getProductCategories(List<Product> products) {
        
        if (products == null) {
            return new HashSet<>();
        }
        return products.stream().map(Product::getCategory).collect(Collectors.toSet());
    }
    
    public double getTotalCategoryValue(List<Product> products, String category) {
        
        if (products == null || category == null) {
            return 0;
        }
        return products.stream().filter(product -> product.getCategory().equals(category)).map(Product::getPrice).reduce(0.0, Double::sum);
    }
}
